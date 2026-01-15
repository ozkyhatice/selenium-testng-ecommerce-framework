package com.automation.api;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import com.automation.models.Product;
import org.testng.Assert;
import java.util.List;


public class ProductApiPojoTest extends ApiBaseTest {
    private static final Logger logger = LogManager.getLogger(ProductApiPojoTest.class);
    @Test
    public void testProductWithPojo() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/productsList");
        List<Product> products = response.jsonPath().getList("products", Product.class);
        Product firstProduct = products.get(0);
        logger.info("First Product Details:");
        logger.info("ID: " + firstProduct.getId());
        logger.info("Name: " + firstProduct.getName());
        logger.info("Price: " + firstProduct.getPrice());
        logger.info("Brand: " + firstProduct.getBrand());
        Assert.assertNotNull(firstProduct.getName());
        Assert.assertFalse(firstProduct.getPrice().isEmpty());
    }
}
