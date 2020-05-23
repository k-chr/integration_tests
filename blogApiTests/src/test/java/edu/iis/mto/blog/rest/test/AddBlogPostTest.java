package edu.iis.mto.blog.rest.test;

import static edu.iis.mto.blog.rest.test.TestConstants.*;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class AddBlogPostTest extends FunctionalTests {
    
    @Test
    void attemptToAddPostByNewUserShouldEndUpWithResponseErrorWithBadRequestStatus() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .body(testJson())
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .when()
                .post(postApiForId(NEW_USER));
    }

    @Test
    void attemptToAddPostByConfirmedUserShouldEndUpWithResponseSuccessWithCreatedStatus() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .body(testJson())
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_CREATED)
                .when()
                .post(postApiForId(USER_CONFIRMED));
    }

    @Test
    void attemptToAddPostByRemovedUserShouldEndUpWithResponseErrorWithBadRequestStatus() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .body(testJson())
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .when()
                .post(postApiForId(REMOVED_USER));
    }

    @Test
    void attemptToAddPostByNotExistingUserShouldEndUpWithResponseErrorWithNotFoundStatus() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .body(testJson())
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .when()
                .post(postApiForId(NOT_EXISTING_USER));
    }

    private String testJson() {
        return new JSONObject().put("entry", "some not important notes").toString();
    }
}
