package com.my.searcher.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.my.searcher.data.entity.db.RecordData;
import com.my.searcher.data.entity.es.RecordIndexedData;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecordDataMapper {

    RecordIndexedData toSportsWatcherIndexedData(RecordData sportsWatcherData);
}
