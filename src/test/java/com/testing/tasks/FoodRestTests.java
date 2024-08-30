package com.testing.tasks;

import com.testing.tasks.models.Product;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import java.util.Map;
import java.util.stream.Stream;

public class FoodRestTests {

    @ParameterizedTest
    @MethodSource("provideFruitsToBeAdded")
    @DisplayName("Проверка корректного добавления фруктов в таблицу")
    void addNewFruitToCollectionTest(Product newProduct) {
        Map<String, String> cookies = given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/food")
                .then()
                .statusCode(200)
                .extract().cookies();
        given()
                .baseUri("http://localhost:8080")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .cookies(cookies)
                .body(newProduct)
                .when()
                .post("/api/food")
                .then()
                .statusCode(200);

        given()
                .baseUri("http://localhost:8080")
                .cookies(cookies)
                .when()
                .get("/food")
                .then()
                .statusCode(200)
                .and()
                .body(contains(newProduct.getName(), newProduct.getType(), newProduct.isExotic()));
//        Product addedProduct = pageManager.getFoodPage()
//                .clickAddFoodButton()
//                .fillAndSubmitFoodForm(newProduct)
//                .getLastRowFromFoodTable();
//        Assertions.assertEquals(newProduct, addedProduct, "Данные поступившего " +
//                "и сохраненного продукта не совпадают");
    }

    @ParameterizedTest
    @MethodSource("provideVegetablesToBeAdded")
    @DisplayName("Проверка корректного добавления овощей в таблицу")
    void addNewVegetableToCollectionTest(Product newProduct) {
//        Product addedProduct = pageManager.getFoodPage()
//                .clickAddFoodButton()
//                .fillAndSubmitFoodForm(newProduct)
//                .getLastRowFromFoodTable();
//        Assertions.assertEquals(newProduct, addedProduct, "Данные поступившего " +
//                "и сохраненного продукта не совпадают");
    }

    private static Stream<Product> provideFruitsToBeAdded() {
        return Stream.of(
                new Product("Мандарин", "FRUIT", true),
                new Product("Груша", "FRUIT", false)
        );
    }

    private static Stream<Product> provideVegetablesToBeAdded() {
        return Stream.of(
                new Product("Огурец", "VEGETABLE", false),
                new Product("Авокадо", "VEGETABLE", true)
        );
    }
}
