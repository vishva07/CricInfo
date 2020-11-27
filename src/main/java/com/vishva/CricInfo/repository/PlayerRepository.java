package com.vishva.CricInfo.repository;

import com.vishva.CricInfo.model.PlayerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity, Integer> {

    PlayerEntity findByPlayerName(String name);
}
