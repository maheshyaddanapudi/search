package com.my.searcher.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;

public class HelperFunctions {

    private HelperFunctions() {}

    private static final String[] SEARCH_FIELDS = {"location", "category", "eventType", "tags"};
    
    public static String buildMultiIndexMatchBody(String query, String field, int from, int size) {
	
		List<String> fields = new ArrayList<String>();
		
		fields.add(field);
    	
    	return "{\n" +
                "\"from\": "+from+",\n" +
                "\"size\": "+size+",\n" +
                "\"track_total_hits\": true,\n" +
                "\"sort\" : {\n" +
                "      \"recordId\": {\"order\": \"asc\"}\n" +
                "      },\n" +
                "  \"query\": {\n" +
                "    \"query_string\" : {\n" +
                "      \"query\":      \"*" + query + "*\",\n" +
                "      \"fields\":     " + new JSONArray(fields) + ",\n" +
                "      \"default_operator\": \"AND\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"highlight\": {\n" +
                "    \"fields\": {\n" +
                "      \"*\": {}\n" +
                "    },\n" +
                "    \"require_field_match\": true\n" +
                " }\n" +
                "}";
    }
    
	public static String buildMultiIndexMatchBody(String query, String field) {
		
			List<String> fields = new ArrayList<String>();
			
			fields.add(field);
	    	
	        return "{\n" +
	                "\"from\": 0,\n" +
	                "\"size\": 100,\n" +
	                "\"track_total_hits\": true,\n" +
	                "\"sort\" : {\n" +
	                "      \"recordId\": {\"order\": \"asc\"}\n" +
	                "      },\n" +
	                "  \"query\": {\n" +
	                "    \"query_string\" : {\n" +
	                "      \"query\":      \"*" + query + "*\",\n" +
	                "      \"fields\":     " + new JSONArray(fields) + ",\n" +
	                "      \"default_operator\": \"AND\"\n" +
	                "    }\n" +
	                "  },\n" +
	                "  \"highlight\": {\n" +
	                "    \"fields\": {\n" +
	                "      \"*\": {}\n" +
	                "    },\n" +
	                "    \"require_field_match\": true\n" +
	                " }\n" +
	                "}";
	    }

    public static String buildMultiIndexMatchBody(String query, List<String> fields, int from, int size) {
    	
    	return "{\n" +
                "\"from\": "+from+",\n" +
                "\"size\": "+size+",\n" +
                "\"track_total_hits\": true,\n" +
                "\"sort\" : {\n" +
                "      \"recordId\": {\"order\": \"asc\"}\n" +
                "      },\n" +
                "  \"query\": {\n" +
                "    \"query_string\" : {\n" +
                "      \"query\":      \"*" + query + "*\",\n" +
                "      \"fields\":     " + new JSONArray(fields) + ",\n" +
                "      \"default_operator\": \"AND\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"highlight\": {\n" +
                "    \"fields\": {\n" +
                "      \"*\": {}\n" +
                "    },\n" +
                "    \"require_field_match\": true\n" +
                " }\n" +
                "}";
    }
    
	public static String buildMultiIndexMatchBody(String query, List<String> fields) {
	    	
	        return "{\n" +
	                "\"from\": 0,\n" +
	                "\"size\": 100,\n" +
	                "\"track_total_hits\": true,\n" +
	                "\"sort\" : {\n" +
	                "      \"recordId\": {\"order\": \"asc\"}\n" +
	                "      },\n" +
	                "  \"query\": {\n" +
	                "    \"query_string\" : {\n" +
	                "      \"query\":      \"*" + query + "*\",\n" +
	                "      \"fields\":     " + new JSONArray(fields) + ",\n" +
	                "      \"default_operator\": \"AND\"\n" +
	                "    }\n" +
	                "  },\n" +
	                "  \"highlight\": {\n" +
	                "    \"fields\": {\n" +
	                "      \"*\": {}\n" +
	                "    },\n" +
	                "    \"require_field_match\": true\n" +
	                " }\n" +
	                "}";
	    }
    
	public static String buildMultiIndexMatchBody(String query, int from, int size) {
	    	
	        return "{\n" +
	                "\"from\": "+from+",\n" +
	                "\"size\": "+size+",\n" +
	                "\"track_total_hits\": true,\n" +
	                "\"sort\" : {\n" +
	                "      \"recordId\": {\"order\": \"asc\"}\n" +
	                "      },\n" +
	                "  \"query\": {\n" +
	                "    \"query_string\" : {\n" +
	                "      \"query\":      \"*" + query + "*\",\n" +
	                "      \"fields\":     " + new JSONArray(Arrays.asList(SEARCH_FIELDS)) + ",\n" +
	                "      \"default_operator\": \"AND\"\n" +
	                "    }\n" +
	                "  },\n" +
	                "  \"highlight\": {\n" +
	                "    \"fields\": {\n" +
	                "      \"*\": {}\n" +
	                "    },\n" +
	                "    \"require_field_match\": true\n" +
	                " }\n" +
	                "}";
	    }
	
    public static String buildMultiIndexMatchBody(String query) {
    	
        return "{\n" +
                "\"from\": 0,\n" +
                "\"size\": 100,\n" +
                "\"track_total_hits\": true,\n" +
                "\"sort\" : {\n" +
                "      \"recordId\": {\"order\": \"asc\"}\n" +
                "      },\n" +
                "  \"query\": {\n" +
                "    \"query_string\" : {\n" +
                "      \"query\":      \"*" + query + "*\",\n" +
                "      \"fields\":     " + new JSONArray(Arrays.asList(SEARCH_FIELDS)) + ",\n" +
                "      \"default_operator\": \"AND\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"highlight\": {\n" +
                "    \"fields\": {\n" +
                "      \"*\": {}\n" +
                "    },\n" +
                "    \"require_field_match\": true\n" +
                " }\n" +
                "}";
    }

    public static String buildSearchUri(String elasticSearchUri,
                                        String elasticSearchIndex,
                                        String elasticSearchSearch) {
        return elasticSearchUri + elasticSearchIndex + elasticSearchSearch;
    }
}