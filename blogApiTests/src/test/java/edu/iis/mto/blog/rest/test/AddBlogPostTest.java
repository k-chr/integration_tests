package edu.iis.mto.blog.rest.test;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class AddBlogPostTest extends FunctionalTests {

    @Test
    void attemptToAddPostByNewUserShouldEndUpWithResponseErrorWithBadRequestStatus() {

    }

    @Test
    void attemptToAddPostByConfirmedUserShouldEndUpWithResponseSuccessWithCreatedStatus() {

    }

    @Test
    void attemptToAddPostByRemovedUserShouldEndUpWithResponseErrorWithBadRequestStatus() {

    }

    @Test
    void attemptToAddPostByNotExistingUserShouldEndUpWithResponseErrorWithNotFoundStatus() {

    }

    private String testJson(){
        return new JSONObject().put("","").toString();
    }
}
