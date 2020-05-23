package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static edu.iis.mto.blog.rest.test.TestConstants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FindPostsOfUserTest extends FunctionalTests {

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
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(postApiForId(POST_OWNER))
                .then()
                .body("likesCount", hasItem(2));
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
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(postApiForId(NEW_USER))
                .then()
                .body("size()", is(equalTo(0)));
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
                .get(postApiForId(ANONYMOUS_USER))
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
