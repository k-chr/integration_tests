package edu.iis.mto.blog.rest.test;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class AddBlogPostTest extends FunctionalTests {

    private final static int USER_NEW = 0x02;
    private final static int USER_CONFIRMED = 0x01;
    private final static int USER_REMOVED = 0x03;
    private final static int USER_THAT_NOT_EXISTS = 0xFFFFFFFF;

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
               .post(createPostApiForId(USER_NEW));
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
                .post(createPostApiForId(USER_CONFIRMED));
    }

    @Test
    void attemptToAddPostByRemovedUserShouldEndUpWithResponseErrorWithBadRequestStatus() {

    }

    @Test
    void attemptToAddPostByNotExistingUserShouldEndUpWithResponseErrorWithNotFoundStatus() {

    }

    private String testJson(){
        return new JSONObject().put("entry","some not important notes").toString();
    }
}
