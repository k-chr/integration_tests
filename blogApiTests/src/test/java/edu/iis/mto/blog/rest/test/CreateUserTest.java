package edu.iis.mto.blog.rest.test;

import static edu.iis.mto.blog.rest.test.TestConstants.*;
import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

public class CreateUserTest extends FunctionalTests {

    @Test
    public void createUserWithProperDataReturnsCreatedStatus() {
        JSONObject jsonObj = new JSONObject().put("email", "tracy1@domain.com");
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .body(jsonObj.toString())
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_CREATED)
                .when()
                .post(userApi());
    }

    @Test
    public void attemptToCreateUserFromPostJsonWithNotUniqueEmailShouldReturnErrorWithHTTPConflictCode() {
        var json = new JSONObject().put("email", "brian@domain.com").toString();
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .body(json)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_CONFLICT)
                .when()
                .post(userApi());
    }
}
