package com.automation.api;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.response.Response;



public class UserCrudTest extends ApiBaseTest {
    String email;
    String password = "Password123!";
    Map<String, Object> userMap = new HashMap<>();
    
    @Test(priority = 1)
    public void createUserTest() {
        email = "practic_" + System.currentTimeMillis() + "@test.com";
        userMap.put("name", "Test Engineer");
        userMap.put("email", email);
        userMap.put("password", password);
        userMap.put("title", "Mr");
        userMap.put("birth_date", "10");
        userMap.put("birth_month", "10");
        userMap.put("birth_year", "1990");
        userMap.put("firstname", "A");
        userMap.put("lastname", "V");
        userMap.put("company", "X");
        userMap.put("address1", "I");
        userMap.put("country", "I");
        userMap.put("zipcode", "00000");
        userMap.put("state", "I");
        userMap.put("city", "I");
        userMap.put("mobile_number", "5555555555");
        Response response = given()
                .spec(requestSpec)
                .formParams(userMap)
            .when()
                .post("/createAccount");
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 2)
    public void updateAccountTest() {
        userMap.put("name", "Updated Test Engineer");
        userMap.put("company", "Updated X");
        Response response = given()
                .spec(requestSpec)
                .formParams(userMap)
            .when()
                .put("/updateAccount");
        Assert.assertEquals(response.getStatusCode(), 200);

    }
    @Test (priority = 3)
    public void getAccountTest() {
        Response response = given()
                .spec(requestSpec)
                .queryParam("email", email)
            .when()
                .get("/getUserDetailByEmail");
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test (priority = 4)
    public void deleteAccountTest() {
        Response response = given()
                .spec(requestSpec)
                .formParam("email", email)
                .formParam("password", password)
            .when()
                .delete("/deleteAccount");
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
