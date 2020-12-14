package com.vishva.CricInfo.repository;

import com.vishva.CricInfo.model.MatchEntity;
import com.vishva.CricInfo.model.OutcomeEntity;
import com.vishva.CricInfo.model.PlayerEntity;
import com.vishva.CricInfo.model.TossEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

    @Query("SELECT m FROM MatchEntity m WHERE m.venue IS :venue AND m.startDate >= :startDate AND m.endDate <= :endDate")
    List<MatchEntity> getMatchEntity(@Param("venue") String venue, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT playerOfMatch FROM MatchEntity m WHERE m.matchType = :match_type AND m.startDate >= :startDate AND m.teams = :teams")
    List<List<String>> getPlayerOfMatch(@Param("match_type") String match_type, @Param("startDate") Date startDate, @Param("teams") List<String> teams);

    @Query("SELECT outcome FROM MatchEntity m WHERE m.venue = :venue AND m.matchType = :matchType")
    //Object[] getOutcome(@Param("venue") String venue, @Param("matchType") String matchType);
    List<OutcomeEntity> getOutcome(@Param("venue") String venue, @Param("matchType") String matchType);

    @Query("SELECT toss FROM MatchEntity m WHERE m.city =:city AND m.venue =:venue")
    List<TossEntity> getToss(@Param("city") String city, @Param("venue") String venue);

    @Query("SELECT playerEntitySet FROM MatchEntity m WHERE m.venue =:venue AND m.matchType =:matchType")
    Set<PlayerEntity> getTeam(@Param("venue") String venue, @Param("matchType") String matchType);

}
