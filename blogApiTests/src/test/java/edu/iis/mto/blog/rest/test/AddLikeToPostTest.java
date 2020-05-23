package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static edu.iis.mto.blog.rest.test.TestConstants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AddLikeToPostTest extends FunctionalTests {

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
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(postApiForPostId(POST_ID))
                .then()
                .body("likesCount", is(equalTo(1)));

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
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(postApiForPostId(POST_ID))
                .then()
                .body("likesCount", is(equalTo(1)));
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
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .post(likeApiForUserIdAndPostId(FIRST_LIKER, POST_ID_2));

        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(postApiForPostId(POST_ID_2))
                .then()
                .body("likesCount", is(equalTo(1)));

        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .post(likeApiForUserIdAndPostId(SECOND_LIKER, POST_ID_2));

        given()
                .accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(postApiForPostId(POST_ID_2))
                .then()
                .body("likesCount", is(equalTo(2)));
    }
}
