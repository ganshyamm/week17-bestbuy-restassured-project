package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import jdk.nashorn.internal.objects.annotations.Where;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.plugin2.os.windows.Windows;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;

public class StoresExtractionTest {
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

    //    1. Extract the limit
    @Test
    public void test001() {

        int limit = response.extract().path("limit");
        System.out.println("The value of store limit is " + limit);

    }

    //    2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("The total value is " + total);
    }


    //    3. Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("Name of 5th Store : " + name);


    }

    //    4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> storeNames = response.extract().path("data.name");
        System.out.println("List of all store names :" + storeNames);


    }


    //    5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> storeIds = response.extract().path("data.id");
        System.out.println("List of all storeId " + storeIds);

    }


    //    6. Print the size of the data list
    @Test
    public void test006() {
        List<HashMap<String, ?>> storeData = response.extract().path("data");
        System.out.println("size of data :" + storeData.size());
    }

    //   7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<Integer> zip = response.extract().path("data.findAll{it.name == 'St Cloud'}.zip");
        System.out.println("Zip of the store where store name is St cloud : " + zip);


    }


    //    8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<String> storeAddress = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("address of the store :" + storeAddress);
    }

//    9. Get all the services of 8th store

    //    10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test0010() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.findAll{it.services.findAll{it.name == 'Windows Store'}}.services.storeservices");
        System.out.println("storeservices at store service name is Windows store :" + storeServices);
    }

    //11. Get all the storeId of all the store
    @Test
    public void test0011() {
        List<Integer> storeID = response.extract().path("data.services.storeservices.storeId");
        System.out.println("all the storeId of all the store" + storeID);


    }

    //    12. Get id of all the store
    @Test
    public void test0012() {

        List<Integer> ID = response.extract().path("data.id");
        System.out.println("id of all the store" + ID);


    }


    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        List<HashMap<String, ?>> storeNames = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("store names where state = ND : " + storeNames);

    }

    //Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<List<String>> services = response.extract().path("data.findAll{it.name == 'Rochester'}.services.name");
        System.out.println(" total number of services :" + services);
        List<String> services9 = services.get(0);
        System.out.println("total number of services for the store Rochester :" + services9.size());
    }

    //Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        List<List<String>> createdAt = response.extract().path("data.findAll{it.services.findAll{it.name =='Windows Store'}}.services.createdAt");
        System.out.println("createdAt for all services for the name = windows store :" + createdAt);
    }

    //Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<String> serviceName = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("name of all servcies at store 'Fargo':" + serviceName);
    }

    // Find the zip of all the store
    @Test
    public void test017() {
        List<String> zip = response.extract().path("data.zip");
        System.out.println("The zip for all stores " + zip);
    }

    // Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<String> zip = response.extract().path("data.findAll{it.name =='Roseville'}.zip");
        System.out.println("The zip of store 'Roseville' is : " + zip);
    }

    //Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.findAll{it.services.findAll{it.name == 'Magnolia Home Theater'}}.services.storeservices");
        System.out.println("storeServices details of the service name 'Magnolia home Theater': " + storeServices);

    }

    //Find the lat of all the stores
    @Test
    public void test020() {
        List<HashMap<String, ?>> lat = response.extract().path("data.lat");
        System.out.println("the latitude of all stores : " + lat);

    }
}
