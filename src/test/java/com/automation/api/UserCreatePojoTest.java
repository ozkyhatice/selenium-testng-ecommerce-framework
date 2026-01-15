package com.automation.api;

import org.testng.annotations.Test;

import com.automation.models.UserRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

public class UserCreatePojoTest extends ApiBaseTest {
    private static final Logger logger = LogManager.getLogger(UserCreatePojoTest.class);
    @Test
    public void testCreateUserWithPojo() {
        UserRequest userRequest = UserRequest.builder()
                .username("testuser")
                .password("password123")
                .email("exm_" + System.currentTimeMillis() + "@test.com")
                .title("Mr")
                .birth_date("10")
                .birth_month("May")
                .birth_year("1990")
                .firstname("Test")
                .lastname("User")
                .company("TestCompany")
                .address1("123 Test St")
                .address2("Apt 4")
                .country("TestCountry")
                .zipcode("12345")
                .state("TestState")
                .city("TestCity")
                .mobile_number("1234567890")
                .name("Test User")
                .build();
        logger.info("user created: " + userRequest);
        Response response = given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(userRequest)
                .post("/createAccount");
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User creation response: " + response.asString());

    }
}
