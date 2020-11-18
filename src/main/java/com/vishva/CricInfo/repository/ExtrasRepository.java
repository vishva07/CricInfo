package com.vishva.CricInfo.repository;

import com.vishva.CricInfo.model.Extras;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtrasRepository extends CrudRepository<Extras, Integer> {

}
