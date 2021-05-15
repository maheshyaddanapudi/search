package com.my.searcher.service.data.es;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.my.searcher.constants.Constants;
import com.my.searcher.data.entity.db.RecordData;
import com.my.searcher.data.repo.db.IRecordDataRepo;
import com.my.searcher.data.repo.es.IRecordndexedDataRepo;
import com.my.searcher.mapper.RecordDataMapper;

@Service
public class ElasticSynchronizer {

	private Logger LOG = LoggerFactory.getLogger(ElasticSynchronizer.class.getSimpleName());
	
	@Autowired
	IRecordDataRepo recordDataRepo;
	
	@Autowired
	IRecordndexedDataRepo recordIndexedDataRepo;
	
	@Autowired
	RecordDataMapper recordDataMapper;
	
	@Value("${api.elasticsearch.uri:http://localhost:9200}")
    private String elasticSearchUri;
	
	@Value("${spring.data.elasticsearch.cluster-name:searcher}")
    private String elasticClusterName;
	
	@Value("${spring.data.elasticsearch.cluster-nodes:localhost:9200}")
    private String elasticClusterNodes;
	
	@Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void sync() {
        LOG.info("Start Syncing - {}", LocalDateTime.now());
        this.syncRecords();
        LOG.info(" End Syncing - {}", LocalDateTime.now());
    }

    private void syncRecords() {
    	
    	LOG.info("Syncing with Elasticsearch at {} with Cluster Name {} and Cluster Nodes {}", elasticSearchUri, elasticClusterName, elasticClusterNodes);
    	
    	Specification<RecordData> recordSpecification = (root, criteriaQuery, criteriaBuilder) ->
        getModificationDatePredicate(criteriaBuilder, root);
		
        List<RecordData> recordDataList;
		
		if (recordIndexedDataRepo.count() == 0) {
			recordDataList = recordDataRepo.findAll();
		} else {
			recordDataList = recordDataRepo.findAll(recordSpecification);
		}
		
		for(RecordData record: recordDataList) {
		    LOG.debug("Syncing Record - {}", record.getRecordId());
		    Gson gson = new Gson();
		    LOG.debug(gson.toJson(this.recordDataMapper.toSportsWatcherIndexedData(record)));
		    recordIndexedDataRepo.save(this.recordDataMapper.toSportsWatcherIndexedData(record));
		}
    }

    private static Predicate getModificationDatePredicate(CriteriaBuilder cb, Root<?> root) {
    	 Expression<Timestamp> currentTime;
         currentTime = cb.currentTimestamp();
         Expression<Timestamp> currentTimeMinus = cb.literal(
                 new Timestamp(System.currentTimeMillis() -
                 (Constants.INTERVAL_IN_MILLISECOND)));
         return cb.between(root.<Date>get(Constants.MODIFICATION_DATE),
                 currentTimeMinus,
                 currentTime
         );
    }
}
