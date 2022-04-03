package com.example;

import com.example.entity.User;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from RESTEasy Reactive"));
    }


    @Test
    public void testUserRegister() {
        User user = new User("john", "johnatan", "password", "email@gmail.com", 'm');

        given()
                .param("user", user)
                .when().post("/auth/register")
                .then()
                .statusCode(200);
    }


}