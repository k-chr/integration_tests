package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static edu.iis.mto.blog.rest.test.TestConstants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class FindUserTest extends FunctionalTests {

    @Test
    void attemptToFindRemovedUserByLastNameShouldEndUpWithResponseSuccessWithOkStatusAndEmptyListOfUsers() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("size()", is(equalTo(0)))
                .when()
                .get(findUserByStringApi(REMOVED_USER_SEARCH_STRING));
    }

    @Test
    void attemptToFindValidUsersByFullNameShouldEndUpWithResponseSuccessWithOkStatusAndNotEmptyResultWithCorrectCount() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("size()", is(equalTo(2)))
                .when()
                .get(findUserByStringApi(VALID_FULL_NAME));
    }

    @Test
    void attemptToFindValidUsersByPartOfNameShouldEndUpWithResponseSuccessWithOkStatusAndNotEmptyResult() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("size()", is(equalTo(1)))
                .when()
                .get(findUserByStringApi(VALID_PART_OF_NAME));
    }

    @Test
    void attemptToFindValidUsersByFullLastNameShouldEndUpWithResponseSuccessWithOkStatusAndNotEmptyResultWithCorrectCount() {

    }

    @Test
    void attemptToFindValidUsersByPartOfLastNameShouldEndUpWithResponseSuccessWithOkStatusAndNotEmptyResult() {

    }

    @Test
    void attemptToFindValidUsersByFullEmailShouldEndUpWithResponseSuccessWithOkStatusAndNotEmptyResultWithCorrectCount() {

    }

    @Test
    void attemptToFindValidUsersByPartOfEmailShouldEndUpWithResponseSuccessWithOkStatusAndNotEmptyResult() {

    }

    @Test
    void attemptToFindValidUsersByInvalidDataShouldEndUpWithResponseSuccessWithOkStatusAndEmptyResult() {

    }

    @Test
    void attemptToFindValidUsersByEmptyStringShouldEndUpWithResponseSuccessWithOkStatusAndResultListThatContainsAllUsersWithoutRemovedOnes() {

    }
}
