package org.Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class JsonReader {


    public List<HashMap<String, String>> getJsonDataToHashMap() throws IOException {

        //json to string
        String jsonContent= FileUtils.readFileToString(new File(System.getProperty("C:\\Users\\ssj\\Framework1\\src\\test\\java\\org\\Data\\TestData.json")));
        //string to hashmap using Jackson Databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String> >data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;









    }
}
