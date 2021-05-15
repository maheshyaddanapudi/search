package com.my.searcher.data.repo.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.my.searcher.data.entity.db.RecordData;

public interface IRecordDataRepo extends JpaRepository<RecordData, Integer> , JpaSpecificationExecutor<RecordData> {

}
