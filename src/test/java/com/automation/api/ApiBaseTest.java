package com.automation.api;

import com.automation.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeClass;

public class ApiBaseTest {
    protected RequestSpecification requestSpec;

    @BeforeClass
    public void apiSetup() {
        RestAssured.baseURI = ConfigReader.get("apiBaseUrl");

        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.URLENC)
                .build();
    }
}