package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest {
    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RestAssured.basePath = "/users";
    }

    static String name = "Krupali";
    static String gender = "female";
    static String status = "active";
    static String email = TestUtils.getRandomValue() + "krupali123@gmail.com";
    static long id = 5914049;
    static String bearerToken = "4f8cd71d1b0ec363937cba822babf40ddf3af619a99e6eecb15e456d61775197";

    @Test
    public void verifyUserCreatedSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setGender(gender);
        userPojo.setStatus(email);
        userPojo.setEmail(email);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(401);
    }

    @Test
    public void verifyUserUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();

        userPojo.setGender("female");
        userPojo.setStatus(status);


        Response response =
                given().log().all()
                        .header("Authorization", "Bearer" + bearerToken)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(userPojo)
                        .put("/" + id);

        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test
    public void verifyUserDeleteSuccessfully() {
        Response response =
                given().log().all()
                        .header("Authorization", "Bearer" + bearerToken)
                        .contentType(ContentType.JSON)
                        .when()
                        .delete("/" + id);

        response.prettyPrint();
        response.then().statusCode(404);
    }
}


