package com.automation.api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.restassured.response.Response;
import com.automation.utils.JsonReader;

import com.automation.utils.Retry;

public class SearchApiDataDrivenTest extends ApiBaseTest {
    private static final Logger logger = LogManager.getLogger(SearchApiDataDrivenTest.class);
    @DataProvider(name = "searchData")
    public Object[][] getSearchData() throws IOException{
        // //read json data
        // byte[] jsonData = Files.readAllBytes(Paths.get("src/test/resources/testData.json"));
        // //convert json data to array with Jackson
        // ObjectMapper objectMapper = new ObjectMapper();
        // List<Map<String,Object>> dataList = objectMapper.readValue(jsonData,  new TypeReference<List<Map<String,Object>>>(){});
        // //prepare data for data provider
        // Object[][] data = new Object[dataList.size()][1];
        // for(int i = 0; i < dataList.size(); i++) {
        //     data[i][0] = dataList.get(i);
        // }
        // return data;

        // Alternative: Use utility class JsonReader
        List<Map<String, Object>> dataList = JsonReader.getTestData("testData.json");
        Object[][] data = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }
        return data;
    }
    @Test(dataProvider = "searchData", retryAnalyzer = Retry.class)
    public void searchProduct(Map<String,Object> data) {
        String searhTerm = (String) data.get("searchTerm");
        logger.info("Searching for product: " + searhTerm);
        Response response = given()
                .spec(requestSpec)
                .formParam("search_product", searhTerm)
                .post("/searchProduct");
        logger.info("Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        logger.info("Search test completed for product: " + searhTerm);
    }
}
