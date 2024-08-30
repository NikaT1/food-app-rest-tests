package com.testing.tasks.base;

import com.testing.tasks.Specifications;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseTests {

    protected static final String BASE_URL = "http://localhost:8080";

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


}
