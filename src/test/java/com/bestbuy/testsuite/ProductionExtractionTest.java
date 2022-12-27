package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import jdk.nashorn.internal.objects.annotations.Where;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductionExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //    21. Extract the limit
    @Test
    public void test0021() {
        int limit = response.extract().path("limit");
        System.out.println("The value of  the product limit is " + limit);
    }

    //22. Extract the total
    @Test
    public void test0022() {
        int productTotal = response.extract().path("total");
        System.out.println("The total value  of product is " + productTotal);

    }

    //23. Extract the name of 5th product
    @Test
    public void test0023() {
        String productName = response.extract().path("data[4].name");
        System.out.println("name of 5th product : " + productName);
    }


    //24. Extract the names of all the products
    @Test
    public void test0024() {
        List<String> productsName = response.extract().path("data.name");
        System.out.println("name of all the products :" + productsName);
    }

    //25. Extract the productId of all the products
    @Test
    public void test0025() {
        List<Integer> productIds = response.extract().path("data.id");
        System.out.println("list of all products : " + productIds);
    }

    //26. Print the size of the data list
    @Test()
    public void test0026() {
        List<HashMap<String, ?>> productData = response.extract().path("data");
        System.out.println("size of the data" + productData.size());

    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4- Pack)
    @Test
    public void test0027() {
        List<Integer> price = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.price");
        System.out.println("value of the product where name is'Energizer - MAX Batteries AA (4- Pack)' :" + price);
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2- Pack)
    @Test
    public void test0028() {
        List<Integer> modelName = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("model of the product where name is 'Energizer - N Cell E90 Batteries (2- Pack)' :" + modelName);
    }

    //29. Get all the categories of 8th products
    @Test
    public void test009() {
        List<HashMap<String, ?>> productCategories = response.extract().path("data[7].categories");
        System.out.println("all categories of 8th product " + productCategories);
    }
//30. Get categories of the store where product id = 150115

//31. Get all the descriptions of all the products
//32. Get id of all the all categories of all the products
//33. Find the product names Where type = HardGood
//34. Find the Total number of categories for the product where product name = Duracell - AA
//1.5V CopperTop Batteries (4-Pack)
//35. Find the createdAt for all products whose price < 5.49
//            36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-
//                                                                                                    Pack)”
//            37. Find the manufacturer of all the products
//38. Find the imge of products whose manufacturer is = Energizer
//39. Find the createdAt for all categories products whose price > 5.99
//            40. Find the uri of all the products
//
}

