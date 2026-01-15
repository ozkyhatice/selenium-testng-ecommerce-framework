package com.automation.api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiSearchTest extends ApiBaseTest {

    private static final Logger logger = LogManager.getLogger(ProductApiTest.class);
    @DataProvider(name = "searchData")
    public Object[][] getSearchData() {
        return new Object[][] {
            {"t-shirt", 200},
            {"jean", 200},
            {"dress", 200}
        };
    }
    @Test(dataProvider = "searchData")
    public void verifySearchWithParameter(String product, int expectedStatusCode) {
        logger.info("Starting test for product search: " + product);
        SoftAssert softAssert = new SoftAssert();
        Response response = given()
                .spec(requestSpec)
                .formParam("search_product", product)
                .when()
                .post("/searchProduct");
        JsonPath jsonPath = response.jsonPath();
        logger.info("Response received: " + response.asString());
        //assertion
        softAssert.assertEquals(response.getStatusCode(), 200, "Incorrect status code");
        softAssert.assertEquals(jsonPath.getInt("responseCode"), expectedStatusCode, "Status code does not match");
        String firstProduct = jsonPath.getString("products[0].name").toLowerCase();
        softAssert.assertTrue(firstProduct.contains(product.toLowerCase()), "First product does not match search term");
        logger.info("Assertions completed for product search: " + product);
        softAssert.assertAll();
    }

}
