package com.testing.tasks.base;

import com.testing.tasks.Specifications;
import com.testing.tasks.models.Product;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseTests {

    protected static final String BASE_URL = "http://localhost:8080";
    protected static final String FOOD_API_ENDPOINT = "/api/food";
    protected static final String RESET_FOOD_API_ENDPOINT = "/api/data/reset";

    @BeforeAll
    static void beforeAll() {
        Specifications.installSpecifications(Specifications.getRequestSpecification(BASE_URL),
                Specifications.getResponseSpecification(200));
    }

    protected Map<String, String> getCookies() {
        return given()
                .when()
                .get("/food")
                .cookies();
    }

    protected void addNewProduct(Map<String, String> cookies, Product product) {
        given()
                .accept(ContentType.JSON)
                .cookies(cookies)
                .body(product)
                .when()
                .post(FOOD_API_ENDPOINT);
    }

    protected List<Product> getProductsList(Map<String, String> cookies) {
        return Arrays.asList(given()
                .cookies(cookies)
                .when()
                .get(FOOD_API_ENDPOINT)
                .then()
                .extract()
                .body().as(Product[].class));
    }

    protected void resetTestProducts(Map<String, String> cookies) {
        given()
                .accept(ContentType.JSON)
                .cookies(cookies)
                .when()
                .post(RESET_FOOD_API_ENDPOINT);
    }

}
