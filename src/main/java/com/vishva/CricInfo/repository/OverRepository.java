package com.vishva.CricInfo.repository;

import com.vishva.CricInfo.entity.OverEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverRepository extends CrudRepository<OverEntity, Integer> {

}
