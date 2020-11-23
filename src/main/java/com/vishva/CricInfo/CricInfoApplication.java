package com.vishva.CricInfo;

import com.google.gson.*;
import com.vishva.CricInfo.entity.*;
import com.vishva.CricInfo.model.*;
import com.vishva.CricInfo.repository.PlayerRepository;
import com.vishva.CricInfo.service.CricDataService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@SpringBootApplication
public class CricInfoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CricInfoApplication.class, args);
	}

    int extras=0, t_runs=0;
	int balls=0, extinn=0, runsinn=0, wicket=0;

	@Autowired
	PlayerRepository playerRepository;

	@Bean
	public CommandLineRunner runner(CricDataService cricDataService) {
		return (args) -> {
			FileFetcher fileFetcher = new FileFetcher();
			List<File> listOfFiles = null;
			try {
				listOfFiles = fileFetcher.getFilesFromFolder("data/");
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(Inning.class, new InningDeserializer());
			Gson gson = builder.create();

			for(File file : listOfFiles) {
				String yaml = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
				String json = YamlConverter.convertYamlToJson(yaml);
				MatchData match = gson.fromJson(json, MatchData.class);
				List<HashMap<String, Inning>> inningArray = match.getInnings();
				for(int i=0; i<inningArray.size(); i++) {
					HashMap<String, Inning> inningObject = inningArray.get(i);
					Inning inn = inningObject.get(inningObject.keySet().toArray()[0]);
					List<Delivery> deliveryArray = inn.getDeliveries();
					double pre = 0.0;
					for(int j=0; j<deliveryArray.size(); j++) {
						Delivery del = deliveryArray.get(j);
						double curr = Double.parseDouble(del.getDeliveryName());
						DeliveryEntity deliveryEntity = createDelivery(del);
						cricDataService.saveDelivery(deliveryEntity);
						if(pre + 0.1 == curr) {
							pre = curr;
							Runs runs = del.getRuns();
							extras += runs.getExtras();
							t_runs += runs.getTotal();
						}
						else {
							pre = curr;
							OverEntity overEntity = createOver(del);
							cricDataService.saveOver(overEntity);
							extras = 0;
							t_runs = 0;
						}
						Runs runs = del.getRuns();
						extinn += runs.getExtras();
						runsinn += runs.getTotal();
						if(del.getWicket() != null)
							wicket++;
					}
					balls = deliveryArray.size();
					InningEntity inningEntity = createInning(inn);
					cricDataService.saveInning(inningEntity);
					extinn = 0;
					runsinn = 0;
					balls = 0;
					wicket = 0;
				}
			}

		};
	}

	public DeliveryEntity createDelivery(Delivery delivery) {
		DeliveryEntity deliveryEntity = new DeliveryEntity();
		if(getPlayerId(delivery.getBatsman()) != 0)
			deliveryEntity.setBatsman(getPlayerId(delivery.getBatsman()));
		else {
			PlayerEntity playerEntity = new PlayerEntity();
			playerEntity.setPlayer_name(delivery.getBatsman());
			playerRepository.save(playerEntity);
			deliveryEntity.setBatsman(getPlayerId(delivery.getBatsman()));
		}
		if(getPlayerId(delivery.getBowler()) != 0)
			deliveryEntity.setBowler(getPlayerId(delivery.getBowler()));
		else {
			PlayerEntity playerEntity = new PlayerEntity();
			playerEntity.setPlayer_name(delivery.getBowler());
			playerRepository.save(playerEntity);
			deliveryEntity.setBowler(getPlayerId(delivery.getBowler()));
		}
		if(getPlayerId(delivery.getNon_striker()) != 0)
			deliveryEntity.setNon_striker(getPlayerId(delivery.getNon_striker()));
		else {
			PlayerEntity playerEntity = new PlayerEntity();
			playerEntity.setPlayer_name(delivery.getNon_striker());
			playerRepository.save(playerEntity);
			deliveryEntity.setNon_striker(getPlayerId(delivery.getNon_striker()));
		}
		Runs runs = delivery.getRuns();
		Extras extras = delivery.getExtras();
		if(extras != null)
			deliveryEntity.setExtrasEntity(createExtras(delivery));
		deliveryEntity.setTotal_runs(runs.getTotal());
		deliveryEntity.setBatsman_runs(runs.getBatsman());
		return deliveryEntity;
	}

	public InningEntity createInning(Inning inning) {
		InningEntity inningEntity = new InningEntity();
		inningEntity.setTeam(inning.getTeam());
		inningEntity.setBalls(balls);
		inningEntity.setExtras(extinn);
		inningEntity.setTotal_score(runsinn);
		inningEntity.setWickets(wicket);
		return inningEntity;
	}

	public ExtrasEntity createExtras(Delivery delivery) {
		ExtrasEntity extrasEntity = new ExtrasEntity();
		Extras extras = delivery.getExtras();
		if(extras.getWides() != 0)
			extrasEntity.setWides(extras.getWides());
		if(extras.getNoballs() != 0)
			extrasEntity.setNoballs(extras.getNoballs());
		if(extras.getLegbyes() != 0)
			extrasEntity.setLegbyes(extras.getLegbyes());
		return extrasEntity;
	}

	public OverEntity createOver(Delivery delivery) {
		OverEntity overEntity = new OverEntity();
		overEntity.setRuns(t_runs);
		overEntity.setExtras(extras);
		overEntity.getDeliveryEntityList().add(createDelivery(delivery));
		return overEntity;
	}

	public int getPlayerId(String player) {
		Iterable<PlayerEntity> playerList = playerRepository.findAll();
		for(PlayerEntity p : playerList) {
			if(p.getPlayer_name().equals(player))
				return p.getId();
		}
		return 0;
	}
}
