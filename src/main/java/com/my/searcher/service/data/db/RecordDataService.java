package com.my.searcher.service.data.db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.my.searcher.constants.Constants;
import com.my.searcher.data.entity.db.RecordData;
import com.my.searcher.data.entity.es.RecordIndexedData;
import com.my.searcher.data.repo.db.IRecordDataRepo;
import com.my.searcher.data.repo.es.IRecordndexedDataRepo;
import com.my.searcher.dto.request.RecordDetail;
import com.my.searcher.dto.response.BaseResponse;
import com.my.searcher.mapper.RecordDataMapper;
import com.my.searcher.service.data.es.ElasticSynchronizer;

@Service
public class RecordDataService {
	
	@Value("${data.update.trigger.cache.clearance:false}")
	public boolean DATA_UPDATE_TRIGGER_CACHE_CLEARANCE;
	
	@Value("${data.update.trigger.elasticsearch.sync:false}")
	public boolean DATA_UPDATE_TRIGGER_ELASTICSEARCH_SYNC;

	@Autowired
	IRecordDataRepo recordDataRepo;
	
	@Autowired
	CacheManager cacheManager;
	
	@Autowired
	ElasticSynchronizer elasticSynchronizer;
	
	@Autowired
	IRecordndexedDataRepo recordndexedDataRepo;
	
	@Autowired
	RecordDataMapper recordDataMapper;
	
	public BaseResponse add(RecordDetail request) {
		
		BaseResponse response = new BaseResponse();
		
		DateFormat df = new SimpleDateFormat("yyyy_mm_dd_hh_MM_ss");
		
		try {
			Date eventDate = df.parse(request.getEventTimestamp_yyyy_mm_dd_hh_MM_ss());
			RecordData newRecordData = new RecordData();
			
			newRecordData.setCategory(request.getCategory());
			newRecordData.setEventTimestamp(eventDate);
			newRecordData.setEventTitle(request.getEventTitle());
			newRecordData.setEventType(request.getEventType());
			newRecordData.setLocation(request.getLocation());
			newRecordData.setTags(request.getTags());
			newRecordData.setFav(request.getFav());
			
			RecordData recordData = recordDataRepo.saveAndFlush(newRecordData);
			
			if(DATA_UPDATE_TRIGGER_ELASTICSEARCH_SYNC) {
				RecordIndexedData recordIndexedData = recordDataMapper.toSportsWatcherIndexedData(recordData);
				recordIndexedData = recordndexedDataRepo.save(recordIndexedData);
				//elasticSynchronizer.sync();
			}
			
			response.setStatus(true);
			response.setMessage(String.valueOf(newRecordData.getRecordId()));
			
		}
		catch(Exception ex) {
			response.setStatus(false);
			response.setMessage("Invalid Date Format - yyyy_mm_dd_hh_MM_ss only accepted");
		}
		
		return response;
		
	}
	
	public BaseResponse update(int recordId, RecordDetail request) {
		
		BaseResponse response = new BaseResponse();
		
		DateFormat df = new SimpleDateFormat("yyyy_mm_dd_hh_MM_ss");
		
		try {
			Date eventDate = df.parse(request.getEventTimestamp_yyyy_mm_dd_hh_MM_ss());
			Optional<RecordData> recordData = recordDataRepo.findById(recordId); 
			
			if(recordData.isPresent())
			{
				recordData.get().setCategory(request.getCategory());
				recordData.get().setEventTimestamp(eventDate);
				recordData.get().setEventTitle(request.getEventTitle());
				recordData.get().setEventType(request.getEventType());
				recordData.get().setLocation(request.getLocation());
				recordData.get().setTags(request.getTags());
				recordData.get().setFav(request.getFav());
				
				recordDataRepo.saveAndFlush(recordData.get());
				
				if(DATA_UPDATE_TRIGGER_CACHE_CLEARANCE) {
					cacheManager.getCache(Constants.CACHE_NAME).clear();
				}
				
				if(DATA_UPDATE_TRIGGER_ELASTICSEARCH_SYNC) {
					RecordIndexedData recordIndexedData = recordDataMapper.toSportsWatcherIndexedData(recordData.get());
					recordIndexedData = recordndexedDataRepo.save(recordIndexedData);
					//elasticSynchronizer.sync();
				}
				
				response.setStatus(true);
				response.setMessage(String.valueOf(recordData.get().getRecordId()));
				
			}
			else {
				response.setStatus(false);
				response.setMessage("404");
			}
			
		}
		catch(Exception ex) {
			response.setStatus(false);
			response.setMessage("Invalid Date Format - yyyy_mm_dd_hh_MM_ss only accepted");
		}
		
		return response;
		
	}
	
	public BaseResponse delete(int recordId) {
		
		BaseResponse response = new BaseResponse();
		
		Optional<RecordData> recordData = recordDataRepo.findById(recordId);
		
		if(recordData.isPresent())
		{
			recordDataRepo.deleteById(recordId);
			
			if(DATA_UPDATE_TRIGGER_CACHE_CLEARANCE) {
				cacheManager.getCache(Constants.CACHE_NAME).clear();
			}
			
			if(DATA_UPDATE_TRIGGER_ELASTICSEARCH_SYNC) {
				recordndexedDataRepo.deleteById(recordId);
				//elasticSynchronizer.sync();
			}
			
			response.setStatus(true);
			response.setMessage(String.valueOf(recordData.get().getRecordId()));
		}
		else
		{
			response.setStatus(false);
			response.setMessage("Invalid Record ID !!!");
		}
		
		return response;
	}
}
