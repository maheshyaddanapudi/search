package com.my.searcher.config.data.sample;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.my.searcher.data.entity.db.RecordData;
import com.my.searcher.data.repo.db.IRecordDataRepo;
import com.my.searcher.service.data.es.ElasticSynchronizer;

@Configuration
@ConditionalOnProperty(name = "loadSample", havingValue = "true", matchIfMissing = true)
public class SampleDataLoadConfiguration {
	
	private Logger LOG = LoggerFactory.getLogger(SampleDataLoadConfiguration.class.getSimpleName());
	
	@Autowired
	IRecordDataRepo recordDataRepo;
	
	@Autowired
	ElasticSynchronizer elasticSynchronizer;

	@EventListener(ApplicationReadyEvent.class)
	public void loadSampleData() {
		
		RecordData newData = new RecordData();
		
		newData.setCategory("Sports");
		newData.setEventTimestamp(new Date());
		newData.setEventTitle("IPL 2021");
		newData.setEventType("Cricket");
		newData.setLocation("Chennai");
		newData.setTags("CSK vs KKR, Dhoni, Raina, Cummins");
		
		LOG.info("Loading Sample Data 1 {}", newData.toString());
		
		
		newData = recordDataRepo.saveAndFlush(newData);
		
		LOG.info("Persisted Sample Data 1 {}", newData.toString());
		
		newData = new RecordData();
		
		newData.setCategory("Sports");
		newData.setEventTimestamp(new Date());
		newData.setEventTitle("EPL 2021");
		newData.setEventType("Football");
		newData.setLocation("London");
		newData.setTags("ManU vs Chelsea");
		
		LOG.info("Loading Sample Data 2 {}", newData.toString());
		
		newData = recordDataRepo.saveAndFlush(newData);
		
		LOG.info("Persisted Sample Data 2 {}", newData.toString());
		
		LOG.info("Performing Elasticsearch Sample Data Sync");
		elasticSynchronizer.sync();
		LOG.info("Completed Elasticsearch Sample Data Sync");
			
		}
}
