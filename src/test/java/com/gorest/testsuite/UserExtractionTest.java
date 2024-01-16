package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
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

    //1. Extract the All Ids
    @Test
    public void test001() {

        List<Integer> listOfIds = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the all Names
    @Test
    public void test002() {

        List<String> names = response.extract().path("name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all name is : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void test003() {

        String name = response.extract().path("[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th object : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {

        List<String> namesOfAllObject = response.extract().path("findAll{it.status == 'inactive'}.name");   //No data so no data.findAll

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th object : " + namesOfAllObject);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {

        List<String> genderOfAllObject = response.extract().path("findAll{it.status == 'active'}.gender");  //Only findAll

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Gender of all the object is : " + genderOfAllObject);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {

        List<String> genderFemale = response.extract().path("findAll{it.gender == 'female'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the object whose gender is female : " + genderFemale);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {

        List<String> allEmails = response.extract().path("findAll{it.status == 'inactive'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The emails of the object where status is inactive: " + allEmails);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test008() {

        List<Integer> idMale = response.extract().path("findAll{it.gender == 'male'}.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of the object where gender is male: " + idMale);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void test009() {

        List<String> allStatus = response.extract().path("status");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Status: " + allStatus);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Lal Dwivedi
    @Test
    public void test010() {

        String email = response.extract().path("[15].email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The email of the object where name = Lal Dwivedi: " + email);
        System.out.println("------------------End of Test---------------------------");
    }


    //11. Get gender of id = 5914189
    @Test
    public void test011() {

        String gender = response.extract().path("[11].gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of id 5914189 is: " + gender);
        System.out.println("------------------End of Test---------------------------");
    }
}
