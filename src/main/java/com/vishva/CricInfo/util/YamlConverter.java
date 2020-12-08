package com.vishva.CricInfo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class YamlConverter {

    private static final ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());

    private static final ObjectMapper jsonWriter = new ObjectMapper();

    public static String convertYamlToJson(String yaml) throws IOException {

        Object obj = yamlReader.readValue(yaml, Object.class);
        return jsonWriter.writeValueAsString(obj);
    }
}