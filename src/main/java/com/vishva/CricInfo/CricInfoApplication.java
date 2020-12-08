package com.vishva.CricInfo;

import com.vishva.CricInfo.util.FileFetcher;
import com.vishva.CricInfo.util.YamlConverter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class CricInfoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CricInfoApplication.class, args);
	}

	@Autowired
	private DataInsertion dataInsertion;

	@Autowired
	private FileFetcher fileFetcher;

	@Bean
	public CommandLineRunner runner() {
		return (args) -> {
			List<File> listOfFiles = fileFetcher.getFilesFromFolder("sample20/");
			assert listOfFiles != null;
			long time = System.currentTimeMillis();
			ExecutorService pool = Executors.newFixedThreadPool(5);
			for(File file : listOfFiles) {
				pool.execute(()-> {
					try {
					    String yaml = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
						String json = YamlConverter.convertYamlToJson(yaml);
						dataInsertion.insertData(json);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
			pool.shutdown();
			pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.MICROSECONDS);
			System.out.printf("Time taken to insert data in db: %f sec", (System.currentTimeMillis() - time) / 1000.0);
		};
	}
}
