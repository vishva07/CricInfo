package com.vishva.CricInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vishva.CricInfo.dto.innings.Inning;
import com.vishva.CricInfo.util.InningDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    Gson gson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Inning.class, new InningDeserializer());
        return builder.create();
    }

    /*@Bean
    public ObjectMapper yamlReader() {
        return new ObjectMapper(new YAMLFactory());
    }*/
}
