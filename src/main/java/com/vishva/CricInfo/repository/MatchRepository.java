package com.vishva.CricInfo.repository;

import com.vishva.CricInfo.model.MatchEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MatchRepository extends CrudRepository<MatchEntity, Long> {

    @Query("SELECT m FROM MatchEntity m WHERE m.venue IS :venue AND m.startDate >= :startDate AND " +
            "m.endDate <= :endDate")
    //@Query("SELECT * FROM MatchEntity")
    MatchEntity getMatchEntity(
            @Param("venue") String venue,
            @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
