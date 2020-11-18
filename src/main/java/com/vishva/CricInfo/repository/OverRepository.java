package com.vishva.CricInfo.repository;

import com.vishva.CricInfo.model.Over;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverRepository extends CrudRepository<Over, Integer> {

}
