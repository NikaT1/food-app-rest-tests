package com.testing.tasks;

import com.testing.tasks.base.BaseTests;
import com.testing.tasks.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FoodRestTest extends BaseTests {

    @ParameterizedTest
    @MethodSource("provideFruitsToBeAdded")
    @DisplayName("Проверка корректного добавления фруктов через API")
    void addNewFruitToCollectionTest(Product newProduct) {
        Map<String, String> cookies = getCookies();
        addNewProduct(cookies, newProduct);
        List<Product> products = getProductsList(cookies);
        resetTestProducts(cookies);
        Assertions.assertEquals(newProduct, products.get(products.size() - 1), "Данные поступившего " +
                "и сохраненного продукта не совпадают");
    }

    @ParameterizedTest
    @MethodSource("provideVegetablesToBeAdded")
    @DisplayName("Проверка корректного добавления овощей в таблицу")
    void addNewVegetableToCollectionTest(Product newProduct) {
        Map<String, String> cookies = getCookies();
        addNewProduct(cookies, newProduct);
        List<Product> products = getProductsList(cookies);
        resetTestProducts(cookies);
        Assertions.assertEquals(newProduct, products.get(products.size() - 1), "Данные поступившего " +
                "и сохраненного продукта не совпадают");
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
