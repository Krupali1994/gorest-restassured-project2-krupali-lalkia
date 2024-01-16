package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

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

    //1. Extract the title
    @Test
    public void test001() {

        List<String> title = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all title is : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void test002() {

        List<Integer> total = response.extract().path("length");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of record is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void test003() {

        String body = response.extract().path("[14].body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th object : : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void test004() {

        List<Integer> allUserId = response.extract().path("user_id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The user_id of all the records is: " + allUserId);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void test005() {

        List<String> titleOfAllRecord = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The itle of all the records is: " + titleOfAllRecord);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Extract the title of all records whose user_id = 5914200
    @Test
    public void test006() {

        String user_id = response.extract().path("[18].title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records whose user_id 5914200 is: " + user_id);
        System.out.println("------------------End of Test---------------------------");
    }

//7. Extract the body of all records whose id = 93957
@Test
public void test007() {

    String id93957 = response.extract().path("[21].body");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The body of all records whose id 93957 is: " + id93957);
    System.out.println("------------------End of Test---------------------------");
}

}
