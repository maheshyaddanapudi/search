package com.my.searcher.service.search.es;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.search.SearchService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.my.searcher.constants.Constants;
import com.my.searcher.dto.response.SearchResult;
import com.my.searcher.dto.response.buffer.SearchResultBuffer;
import com.my.searcher.mapper.RecordDataMapper;
import com.my.searcher.utils.HelperFunctions;
import com.my.searcher.utils.ResultQuery;

@Service
@CacheConfig(cacheNames = Constants.CACHE_NAME)
public class SearcherService {

	 	@Value("${api.elasticsearch.uri:http://localhost:9200}")
	    private String elasticSearchUri;

	    @Value("${api.elasticsearch.search:_search}")
	    private String elasticSearchSearchPrefix;
	    
	    @Autowired
	    RecordDataMapper mapper;

	    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

	    @Cacheable
	    public ResultQuery searchFromQuery(String query) throws IOException {
	        String body = HelperFunctions.buildMultiIndexMatchBody(query);
	        return executeHttpRequest(body);
	    }
	    
	    @Cacheable
	    public ResultQuery searchFromQuery(String query, int from, int size) throws IOException {
	        String body = HelperFunctions.buildMultiIndexMatchBody(query, from, size);
	        return executeHttpRequest(body);
	    }
	    
	    @Cacheable
	    public ResultQuery searchFromQuery(String query, String field) throws IOException {
	        String body = HelperFunctions.buildMultiIndexMatchBody(query, field);
	        return executeHttpRequest(body);
	    }
	    
	    @Cacheable
	    public ResultQuery searchFromQuery(String query, String field, int from, int size) throws IOException {
	        String body = HelperFunctions.buildMultiIndexMatchBody(query, field, from, size);
	        return executeHttpRequest(body);
	    }
	    
	    @Cacheable
	    public ResultQuery searchFromQuery(String query, List<String> fields) throws IOException {
	        String body = HelperFunctions.buildMultiIndexMatchBody(query, fields);
	        return executeHttpRequest(body);
	    }
	    
	    @Cacheable
	    public ResultQuery searchFromQuery(String query, List<String> fields, int from, int size) throws IOException {
	        String body = HelperFunctions.buildMultiIndexMatchBody(query, fields, from, size);
	        return executeHttpRequest(body);
	    }

		private ResultQuery executeHttpRequest(String body) throws IOException{
	        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
	            ResultQuery resultQuery = new ResultQuery();
	            HttpPost httpPost = new HttpPost(HelperFunctions.buildSearchUri(elasticSearchUri
	                    , "", "/"+elasticSearchSearchPrefix));
	            httpPost.setHeader(Constants.CONTENT_ACCEPT, Constants.APP_TYPE);
	            httpPost.setHeader(Constants.CONTENT_TYPE, Constants.APP_TYPE);
	            try {
	                httpPost.setEntity(new StringEntity(body, Constants.ENCODING_UTF8));
	                HttpResponse response = httpClient.execute(httpPost);
	                String message = EntityUtils.toString(response.getEntity());
	                JSONObject myObject = new JSONObject(message);
	                if(myObject.getJSONObject(Constants.HITS)
	                        .getJSONObject(Constants.TOTAL_HITS).getInt(Constants.VALUE) != 0){
	                    
	                    List<SearchResult> searchResults = new ArrayList<SearchResult>();
	                    Gson gson = new Gson();
	                    
	                    myObject
                        .getJSONObject(Constants.HITS)
                        .getJSONArray(Constants.HITS).forEach(hit -> {
                        	SearchResultBuffer aSearchResultBuffer = gson.fromJson(((JSONObject) hit).get("_source").toString(), SearchResultBuffer.class);
                        	SearchResult aSearchResult = new SearchResult(
                        			aSearchResultBuffer.getRecordId(), 
                        			aSearchResultBuffer.getEventTitle(), 
                        			aSearchResultBuffer.getLocation(), 
                        			aSearchResultBuffer.getInsertTimestamp(), 
                        			aSearchResultBuffer.getEventType(), 
                        			aSearchResultBuffer.getCategory(), 
                        			aSearchResultBuffer.getFav(),
                        			aSearchResultBuffer.getUpdateTimestamp(), 
                        			aSearchResultBuffer.getEventTimestamp(), 
                        			aSearchResultBuffer.getTags());
                        	
                        	searchResults.add(aSearchResult);
                        });
	                    
	                    resultQuery.setResults(searchResults);
	                    
	                    resultQuery
	                            .setNumberOfResults(myObject.getJSONObject(Constants.HITS)
	                            		.getJSONObject(Constants.TOTAL_HITS).getInt(Constants.VALUE));
	                    resultQuery.setTimeTaken((float) ((double) myObject.getInt(Constants.TOOK) / Constants.TO_MS));
	                } else {
	                    resultQuery.setNumberOfResults(0);
	                    resultQuery.setTimeTaken((float) ((double) myObject.getInt(Constants.TOOK) / Constants.TO_MS));
	                }
	            } catch (IOException | JSONException e) {
	                LOGGER.error("Error while connecting to elastic engine --> {}", e.getMessage());
	                resultQuery.setNumberOfResults(0);
	            }

	            return resultQuery;
	        }
	    }
	}