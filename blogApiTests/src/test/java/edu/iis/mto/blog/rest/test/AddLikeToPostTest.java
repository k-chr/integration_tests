package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AddLikeToPostTest extends FunctionalTests {

    private final static int FIRST_LIKER = 0x04;
    private final static int NEW_USER = 0x02;
    private final static int REMOVED_USER = 0x03;
    private final static int POST_OWNER = 0x01;
    private final static int SECOND_LIKER = 0x05;
    private final static int NOT_EXISTING_USER = 0xFFFFFFFF;
    private final static int ANONYMOUS_LIKER = 0x06;
    private final static int POST_ID = 0x01;
    private final static int POST_ID_2 = 0x02;
    private final static int POST_FOR_ANON = 0x03;

    @Test
    void attemptToAddLikeToPostByUserThatHasNewAccountShouldEndUpWithResponseErrorWithBadRequestStatus() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .when()
                .post(likeApiForUserIdAndPostId(NEW_USER, POST_ID));
    }

    @Test
    void attemptToAddLikeToPostByUserThatBeforeAddedLikeToThatPostShouldEndUpWithNoChangeInLikesCountOfThatPost() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .post(likeApiForUserIdAndPostId(FIRST_LIKER, POST_ID));

        given().accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(postApiForId(POST_OWNER))
                .then()
                .body("likesCount", hasItem(1));

        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .post(likeApiForUserIdAndPostId(FIRST_LIKER, POST_ID));

        given()
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(postApiForId(POST_OWNER))
                .then()
                .body("likesCount", hasItem(1));
    }

    @Test
    void attemptToAddLikeToPostByUserWhoCreatedThatPostShouldEndUpWithResponseErrorWithBadRequestStatus() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .when()
                .post(likeApiForUserIdAndPostId(POST_OWNER, POST_ID));
    }

    @Test
    void attemptToAddLikeToPostByUserThatHasRemovedAccountShouldEndUpWithResponseErrorWithBadRequestStatus() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .when()
                .post(likeApiForUserIdAndPostId(REMOVED_USER, POST_ID));
    }

    @Test
    void attemptToAddLikeToPostByUserThatNotExistsShouldEndUpWithResponseErrorWithNotFoundStatus() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .when()
                .post(likeApiForUserIdAndPostId(NOT_EXISTING_USER, POST_ID));
    }

    @Test
    void attemptToAddLikeToPostByUserThatHasConfirmedAccountAndIsNotOwnerOfPostShouldEndUpWithResponseSuccessWithOkStatus() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .post(likeApiForUserIdAndPostId(ANONYMOUS_LIKER, POST_FOR_ANON));
    }

    @Test
    void attemptToAddLikeToPostByAnotherConfirmedUserShouldEndUpWithChangeInLikesCountOfThatPost() {

    }
}
