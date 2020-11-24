package com.vishva.CricInfo;

import com.google.gson.*;
import com.vishva.CricInfo.entity.*;
import com.vishva.CricInfo.model.*;
import com.vishva.CricInfo.repository.PlayerRepository;
import com.vishva.CricInfo.service.CricDataService;
import com.vishva.CricInfo.util.FileFetcher;
import com.vishva.CricInfo.util.InningDeserializer;
import com.vishva.CricInfo.util.YamlConverter;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class CricInfoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CricInfoApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(CricDataService cricDataService, PlayerRepository playerRepository) {
		return (args) -> {
			FileFetcher fileFetcher = new FileFetcher();
			List<File> listOfFiles = fileFetcher.getFilesFromFolder("data/");
			assert listOfFiles != null;
			long time = System.currentTimeMillis();

			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(Inning.class, new InningDeserializer());
			Gson gson = builder.create();

			for(File file : listOfFiles) {
				//insertData(file, gson, cricDataService, playerRepository);
				ExecutorService pool = Executors.newFixedThreadPool(5);
				pool.execute(()-> {
					try {
						insertData(file, gson, cricDataService, playerRepository);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				pool.shutdown();
				pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.MICROSECONDS);
			}
			System.out.printf("Time taken to insert data in db: %f sec", (System.currentTimeMillis() - time) / 1000.0);
		};
	}

	private void insertData(File file, Gson gson, CricDataService cricDataService, PlayerRepository playerRepository) throws IOException {

		String yaml = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		String json = YamlConverter.convertYamlToJson(yaml);
		MatchData match = gson.fromJson(json, MatchData.class);
		List<HashMap<String, Inning>> inningArray = match.getInnings();
		for (HashMap<String, Inning> inningObject : inningArray) {
			Inning inn = inningObject.get(inningObject.keySet().toArray()[0]);
			List<Delivery> deliveryArray = inn.getDeliveries();
			InningEntity inningEntity = Aggregation.populateData(inn, deliveryArray, playerRepository, cricDataService);
			cricDataService.saveInning(inningEntity);
		}
	}
}
