package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size()", equalTo(20));
    }

    //2. Verify the if the name of id = 5914070 is equal to ”Bhargavi Shah JD”
    @Test
    public void test002() {
        response.body("[0].name", equalTo("Bhargavi Shah JD"));
    }

    //3. Check the single ‘Name’ in the Array list (Heema Kaniyar)
    @Test
    public void test003() {
        response.body("[19].name", equalTo("Heema Kaniyar"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Goswami Prajapat, Abhaya Mahajan I, Dr. Vishwamitra Bhattacharyav)
    @Test
    public void test004() {
        response.body("name", hasItems("Goswami Prajapat", "Abhaya Mahajan I", "Dr. Vishwamitra Bhattacharya"));
    }

    //5. Verify the email of userid = 5914058 is equal “embranthiri_akshat@pfeffer.test”
    @Test
    public void test005() {
        response.body("[10].email", equalTo("embranthiri_akshat@pfeffer.test"));
    }

    //6. Verify the status is “Active” of user name is “Prof. Triloki Dhawan”
    @Test
    public void test006() {
        response.body("[9].status", equalTo("active"));
    }


//7. Verify the Gender = male of user name is “Pres. Bhagvan Deshpande”
    @Test
    public void test007() {
        response.body("[5].gender", equalTo("male"));
    }

}
