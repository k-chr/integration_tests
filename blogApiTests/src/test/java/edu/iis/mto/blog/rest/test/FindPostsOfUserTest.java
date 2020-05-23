package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FindPostsOfUserTest extends FunctionalTests {

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
    void attemptToFindPostsOfRemovedUserShouldEndUpWithResponseErrorWithBadRequestStatus() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .when()
                .get(postApiForId(REMOVED_USER));
    }

    @Test
    void attemptToFindPostsOfValidUserShouldEndUpWithResponseSuccessWithOkStatusAndLikedPostWithCorrectCountOfLikes() {

    }

    @Test
    void attemptToFindPostsOfNonExistingUserShouldEndUpWithResponseErrorWithNotFoundStatus() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .when()
                .get(postApiForId(NOT_EXISTING_USER));
    }

    @Test
    void attemptToFindPostsOfValidUserWhoHasNoPostsShouldEndUpWithResponseSuccessWithOkStatusAndEmptyListOfPosts() {

    }

    @Test
    void attemptToFindPostsOfConfirmedUserWhoHasPostsButWithoutLikesShouldEndUpWithResponseSuccessWithOkStatusAndListOfPostsWithoutLikes() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(postApiForId(ANONYMOUS_LIKER))
                .then()
                .body("size()", is(equalTo(1)))
                .body("likesCount", hasItem(0));
    }

    @Test
    void attemptToFindPostsOfValidUserWhoHasPostsShouldEndUpWithResponseSuccessWithOkStatusAndListOfPostsWithCorrectCount() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(postApiForId(POST_OWNER))
                .then()
                .body("size()", is(equalTo(4)));
    }
}
