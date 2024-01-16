package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size()", equalTo(25));
    }

    //2. Verify the if the title of id = 93997 is equal to ”Textus nihil voluptatem autem totam consectetur accusantium.”
    @Test
    public void test002() {
        response.body("[1].title", equalTo("Textus nihil voluptatem autem totam consectetur accusantium."));
    }

    //3. Check the single user_id in the Array list (5914181)
    @Test
    public void test003() {
        response.body("[3].user_id", equalTo(5914181));
    }

    //4. Check the multiple ids in the ArrayList (55914051, 5914051, 5914050)
    @Test
    public void test004() {
        response.body("user_id", hasItems(5914051, 5914051, 5914050));
    }

    //5. Verify the body of userid = 5914197 is equal “Usque tametsi terror. Terror verbum arbustum. Voveo appositus peior. Et surgo crapula. Verbera currus deinde. Convoco teres vilis. Volup consequatur coadunatio. Cruciamentum acceptus tonsor. Defungo aut sulum. Molestias aut subiungo. Unde adulatio supplanto. Stabilis laborum canis. Suggero bellum quibusdam. Animus amitto centum. Sollers brevis officia. Dens odio tenus. Bestia beatus suffoco. Aegrus auditor crur. Vinco articulus tutis.”
    @Test
    public void test005() {
        response.body("[20].body", equalTo("Usque tametsi terror. Terror verbum arbustum. Voveo appositus peior. Et surgo crapula. Verbera currus deinde. Convoco teres vilis. Volup consequatur coadunatio. Cruciamentum acceptus tonsor. Defungo aut sulum. Molestias aut subiungo. Unde adulatio supplanto. Stabilis laborum canis. Suggero bellum quibusdam. Animus amitto centum. Sollers brevis officia. Dens odio tenus. Bestia beatus suffoco. Aegrus auditor crur. Vinco articulus tutis."));
    }

}
