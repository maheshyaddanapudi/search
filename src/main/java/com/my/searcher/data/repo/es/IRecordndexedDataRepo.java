package com.my.searcher.data.repo.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.my.searcher.data.entity.es.RecordIndexedData;

public interface IRecordndexedDataRepo extends ElasticsearchRepository<RecordIndexedData, Integer> {

}
