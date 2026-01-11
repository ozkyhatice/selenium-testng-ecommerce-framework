package com.automation.api;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.List;

public class ProductApiTest {
    @Test
    public void verifyAllProductsTest() {
        SoftAssert softAssert = new SoftAssert();
        RestAssured.baseURI = "https://automationexercise.com/api";
        Response response = get("/productsList");
        JsonPath jsonPath = response.jsonPath();
        //status code "200"
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        //verify that product list is not empty
        List<Object> products = jsonPath.getList("products");
        softAssert.assertEquals(products.size() > 0, true, "Product list is empty");
        String firstProductName = jsonPath.getString("products[0].name");
        softAssert.assertNotNull(firstProductName, "First product name is null");
        String firstProductPrice = jsonPath.getString("products[0].price");
        softAssert.assertNotNull(firstProductPrice, "First product price is null");
        //price must contain Rs
        softAssert.assertTrue(firstProductPrice.contains("Rs"), "First product price does not contain 'Rs'");
        String firstProductCategory = jsonPath.getString("products[0].category.category");
        softAssert.assertNotNull(firstProductCategory, "First product category is null");
        softAssert.assertAll();
    }
    @Test
    public void verifySearchWithoutParameter() {
        SoftAssert softAssert = new SoftAssert();
        RestAssured.baseURI = "https://automationexercise.com/api";
        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                // .formParam("search_product",  "t-shirt")
                .when()
                .post("/searchProduct")
                .then()
                .extract().response();
        //assertion
        JsonPath jsonPath = response.jsonPath();
        // api code must return 400
        int apiResponseCode = jsonPath.getInt("responseCode");
        String apiMessage = jsonPath.getString("message");
        softAssert.assertEquals(apiResponseCode, 400, "API response code is not 400");
        softAssert.assertTrue(apiMessage.contains("missing"), "API message does not indicate missing parameter");
        System.out.println("API Response Message: " + apiMessage);
        softAssert.assertAll();
    }
    
}
