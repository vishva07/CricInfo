package com.vishva.CricInfo.util.entitycreator;

import com.vishva.CricInfo.dto.innings.Delivery;
import com.vishva.CricInfo.dto.innings.Inning;
import com.vishva.CricInfo.model.PlayerEntity;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PlayerMapper {

    public Set<PlayerEntity> getPlayerEntities(List<String> teams, List<HashMap<String, Inning>> innings) {
        Set<PlayerEntity> playerEntities = new HashSet<>();

        HashMap<String, Set<String>> teamMap = new HashMap<>();
        teams.forEach(team -> teamMap.put(team, new HashSet<>()));

        innings.forEach(inningHashMap -> inningHashMap.forEach((inningName, inning) -> {
            String team = inning.getTeam();
            String team2 = teams.get(0).equals(team) ? teams.get(1) : teams.get(0);

            List<Delivery> deliveries = inning.getDeliveries();
            deliveries.forEach(delivery -> {
                teamMap.get(team).add(delivery.getBatsman());
                teamMap.get(team).add(delivery.getNon_striker());
                teamMap.get(team2).add(delivery.getBowler());
            });
        }));

        teamMap.forEach((team, players) ->
                players.forEach(playerName ->
                        playerEntities.add(new PlayerEntity(playerName, team, null))));

        return playerEntities;
    }
}
