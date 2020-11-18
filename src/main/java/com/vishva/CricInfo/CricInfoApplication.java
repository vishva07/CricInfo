package com.vishva.CricInfo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vishva.CricInfo.model.Delivery;
import com.vishva.CricInfo.model.Extras;
import com.vishva.CricInfo.model.Inning;
import com.vishva.CricInfo.model.Over;
import com.vishva.CricInfo.service.CricDataService;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class CricInfoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CricInfoApplication.class, args);
	}

    int extras=0, t_runs=0;
	int balls=0, extinn=0, runsinn=0, wicket=0;
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

			for(File file : listOfFiles) {
				String yaml = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
				String json = YamlConverter.convertYamlToJson(yaml);
				JsonObject rootObject = JsonDeserializer.deserializeJson(json);
				JsonArray inningArray = rootObject.getAsJsonArray("innings");
				for(int i=0; i<inningArray.size(); i++) {
					JsonElement inning = inningArray.get(i);
					JsonObject inningObject = inning.getAsJsonObject();
					List<String> innName = inningObject.entrySet().stream().map(k -> k.getKey()).collect(Collectors.toCollection(ArrayList::new));
					JsonObject inn = inningObject.getAsJsonObject(innName.get(0));
					/*Inning inning1 = createInning(inn);
					cricDataService.saveInning(inning1);*/
					JsonArray deliveryArray = inn.getAsJsonArray("deliveries");
					double pre = 0.0;
					for(int j=0; j<deliveryArray.size(); j++) {
						JsonElement perDelivery = deliveryArray.get(j);
						JsonObject perDeliveryObject = perDelivery.getAsJsonObject();
						List<String> perDelName = perDeliveryObject.entrySet().stream().map(k -> k.getKey()).collect(Collectors.toCollection(ArrayList::new));
						double curr = Double.parseDouble(perDelName.get(0));
						JsonObject del = perDeliveryObject.getAsJsonObject(perDelName.get(0));
						if(del.has("extras")) {
							Extras extras = createExtras(del);
							cricDataService.saveExtras(extras);
						}
						Delivery delivery = createDelivery(del);
						cricDataService.saveDelivery(delivery);
						if(pre + 0.1 == curr) {
							pre = curr;
							JsonObject runs = del.getAsJsonObject("runs");
							extras += runs.get("extras").getAsInt();
							t_runs += runs.get("total").getAsInt();
						}
						else {
							pre = curr;
							Over over = createOver(del);
							cricDataService.saveOver(over);
							extras = 0;
							t_runs = 0;
						}
						JsonObject runs = del.getAsJsonObject("runs");
						extinn += runs.get("extras").getAsInt();
						runsinn += runs.get("total").getAsInt();
						if(del.has("wicket"))
							wicket++;
					}
					balls = deliveryArray.size();
					Inning inning1 = createInning(inn);
					cricDataService.saveInning(inning1);
					extinn = 0;
					runsinn = 0;
					balls = 0;
					wicket = 0;
				}
			}
		};
	}

	public Delivery createDelivery(JsonObject delivery) {
		Delivery deliveryEntity = new Delivery();
		deliveryEntity.setBatsman(delivery.get("batsman").getAsString());
		deliveryEntity.setBowler(delivery.get("bowler").getAsString());
		deliveryEntity.setNon_striker(delivery.get("non_striker").getAsString());
		JsonObject runs = delivery.getAsJsonObject("runs");
		
		deliveryEntity.setTotal_runs(runs.get("total").getAsInt());
		deliveryEntity.setBatsman_runs(runs.get("batsman").getAsInt());
		return deliveryEntity;
	}

	public Inning createInning(JsonObject inning) {
		Inning inningEntity = new Inning();
		inningEntity.setTeam(inning.get("team").getAsString());
		inningEntity.setBalls(balls);
		inningEntity.setExtras(extinn);
		inningEntity.setTotal_score(runsinn);
		inningEntity.setWickets(wicket);
		return inningEntity;
	}

	public Extras createExtras(JsonObject delivery) {
		Extras extrasEntity = new Extras();
		JsonObject extras = delivery.getAsJsonObject("extras");
		if(extras.has("wides"))
			extrasEntity.setWides(extras.get("wides").getAsInt());
		if(extras.has("noballs"))
			extrasEntity.setNoballs(extras.get("noballs").getAsInt());
		if(extras.has("legbyes"))
			extrasEntity.setLegbyes(extras.get("legbyes").getAsInt());
		return extrasEntity;
	}

	public Over createOver(JsonObject delivery) {
		Over overEntity = new Over();
		overEntity.setRuns(t_runs);
		overEntity.setExtras(extras);
		return overEntity;
	}

}
