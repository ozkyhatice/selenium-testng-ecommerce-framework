package com.automation.api;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import org.testng.asserts.SoftAssert;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;


public class ApiSchemaTest extends ApiBaseTest{
    @Test
    public void verifyProductsSchema0() {

        Response response = given()
            .spec(requestSpec)
        .when()
            .get("/productsList");
        List<Map<String, Object>> products = response.jsonPath().getList("products");
        SoftAssert softAssert = new SoftAssert();
        int statusCode = response.jsonPath().getInt("responseCode");
        softAssert.assertEquals(statusCode, 200, "Incorrect response code");
        softAssert.assertFalse(products.isEmpty(), "Product list is empty");
        for (Map<String, Object> product : products) {
            softAssert.assertTrue(product.get("id") instanceof Integer, "Product ID is not an Integer");
            softAssert.assertTrue(product.get("name") instanceof String, "Product name is not a String");
            softAssert.assertTrue(product.get("price") instanceof String, "Product price is not a String");
            softAssert.assertTrue(product.get("category") instanceof Map, "Product category is not a Map");
        }
        softAssert.assertAll();
    }
    // JSON Schema validation test
    @Test
    public void verifyProductsSchema() {
        given()
            .spec(requestSpec)
        .when()
            .get("/productsList")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("productsSchema.json"));
    }
}
