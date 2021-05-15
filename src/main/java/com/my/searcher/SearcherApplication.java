package com.my.searcher;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.elasticsearch.ElasticSearchRestHealthContributorAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.my.searcher.service.data.es.ElasticSynchronizer;

@SpringBootApplication(exclude = ElasticSearchRestHealthContributorAutoConfiguration.class)
@EnableElasticsearchRepositories("com.my.searcher.data.repo.es")
@EnableScheduling
@EnableCaching
@CrossOrigin("*")
public class SearcherApplication {
	
	private Logger LOG = LoggerFactory.getLogger(SearcherApplication.class.getSimpleName());
	
	@Autowired
	ElasticSynchronizer elasticSynchronizer;
	
	@Value("${startup.elasticsearch.sync.enabled:false}")
	public boolean ELASTICSEARCH_STARTUP_SYNC_ENABLED;
	
	@PostConstruct
	  public void init(){
	    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(SearcherApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void syncElasticSeach() {
		
		if(ELASTICSEARCH_STARTUP_SYNC_ENABLED)
		{
			LOG.info("Performing Elasticsearch Startup Sync");
			elasticSynchronizer.sync();
			LOG.info("Completed Elasticsearch Startup Sync");
		}
		else
			LOG.warn("Skipping Elasticsearch Startup Sync as startup.elasticsearch.sync.enabled is set to {}", ELASTICSEARCH_STARTUP_SYNC_ENABLED);
		
		
		
	}
}
