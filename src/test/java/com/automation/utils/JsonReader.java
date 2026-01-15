package com.automation.utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.File;



public class JsonReader {
    public static List<Map<String, Object>> getTestData(String filePath) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(
            new File("src/test/resources/" + filePath),
                new TypeReference<List<Map<String,Object>>>(){}
        );
    }
}
