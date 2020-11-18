package com.vishva.CricInfo.repository;

import com.vishva.CricInfo.model.Inning;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InningRepository extends CrudRepository<Inning, Integer> {

}
