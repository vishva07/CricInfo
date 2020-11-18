package com.vishva.CricInfo.repository;

import com.vishva.CricInfo.model.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Integer> {

}
