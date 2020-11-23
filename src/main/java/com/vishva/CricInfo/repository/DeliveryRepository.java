package com.vishva.CricInfo.repository;

import com.vishva.CricInfo.entity.DeliveryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends CrudRepository<DeliveryEntity, Integer> {

}
