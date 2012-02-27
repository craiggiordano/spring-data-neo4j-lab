package com.ierin.neo4j.repository;

import com.ierin.neo4j.domain.Lab;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 */
public interface LabRepository extends GraphRepository<Lab>, NamedIndexRepository<Lab> {

    @Query("start lab=node:SINGLE_STRING_INDEX(single={single}) return lab")
    public List<Lab> findLabBySingleString(@Param("single") String single);

    @Query("start lab=node:MULTIPLE_STRING_INDEX({searchKey}) return lab")
    public List<Lab> findLabByMultipleString(@Param("searchKey") String searchKey);

    @Query("start lab=node:SINGLE_LONG_INDEX(singleLong={single}) return lab")
    public List<Lab> findLabBySingleLong(@Param("single") Long single);

    @Query("start lab=node:MULTIPLE_LONG_INDEX({searchKey}) return lab")
    public List<Lab> findLabByMultipleLong(@Param("searchKey") String searchKey);
}
