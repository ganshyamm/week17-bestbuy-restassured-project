package com.bestbuy.crudetest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProductTest extends TestBase {
    @Test
    public void getAllProductsInfo() {
        Response response = given()
                .when()
                .get("/products");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getSingleProductInfo() {
        Response response = given()
                .pathParam("id", 43900)
                .when()
                .get("/products/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

}
    @Test
    public void searchProductWithParameter() {
        Map<String, Object> qParams = new HashMap<>();
        qParams.put("manufacturer", "Duracell");
        Response response = given()
                .queryParams(qParams)
                .when()
                .get("/products");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void createProduct() {

        List<String> categories = new ArrayList<>();
        categories.add("PowerBank");
        categories.add("LongLastingBattery");
        ProductPojo productPojo = new ProductPojo();
        productPojo.setId(104);
        productPojo.setName("Duracell power bank");
        productPojo.setCreatedAt(20345);
        productPojo.setModel("Google");
        productPojo.setPrice(20);
        productPojo.setCategories(categories);
        Response response = given()
                .basePath("/products")
                .header("Content-Type", "application/json")
                .body(productPojo)
                .when()
                .post();
        response.then().log().all().statusCode(201);
    }

    @Test
    public void deleteData() {

        Response response = given()
                .basePath("/products")
                .pathParam("price", 5.49)
                .when()
                .delete("/{price}");
        response.then().statusCode(404);
        response.prettyPrint();

    }

    @Test
    public void patchRecord() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.getPrice();
        Response response = given()
                .basePath("/products")
                .header("Content-Type", "application/json")
                .body(productPojo)
                .pathParam("price", 600)
                .patch();
        response.then().statusCode(200);
        response.prettyPrint();

    }



}

