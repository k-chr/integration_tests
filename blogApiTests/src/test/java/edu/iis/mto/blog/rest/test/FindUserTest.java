package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static edu.iis.mto.blog.rest.test.TestConstants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.empty;

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
                .body(GET_LIST, is(empty()))
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
                .body(GET_LIST, is(not(empty())))
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
                .body(GET_LIST, is(not(empty())))
                .when()
                .get(findUserByStringApi(VALID_PART_OF_NAME));
    }

    @Test
    void attemptToFindValidUsersByFullLastNameShouldEndUpWithResponseSuccessWithOkStatusAndNotEmptyResultWithCorrectCount() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(GET_LIST, is(not(empty())))
                .when()
                .get(findUserByStringApi(VALID_FULL_LAST_NAME));
    }

    @Test
    void attemptToFindValidUsersByPartOfLastNameShouldEndUpWithResponseSuccessWithOkStatusAndNotEmptyResult() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(GET_LIST, is(not(empty())))
                .when()
                .get(findUserByStringApi(VALID_PART_OF_LAST_NAME));
    }

    @Test
    void attemptToFindValidUsersByFullEmailShouldEndUpWithResponseSuccessWithOkStatusAndNotEmptyResultWithCorrectCount() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(GET_LIST, is(not(empty())))
                .when()
                .get(findUserByStringApi(VALID_FULL_EMAIL));
    }

    @Test
    void attemptToFindValidUsersByPartOfEmailShouldEndUpWithResponseSuccessWithOkStatusAndNotEmptyResult() {
        given().accept(ContentType.JSON)
                .header(TYPE, OPTION_JSON)
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(GET_LIST, is(not(empty())))
                .when()
                .get(findUserByStringApi(VALID_PART_OF_EMAIL));
    }

    @Test
    void attemptToFindValidUsersByInvalidDataShouldEndUpWithResponseSuccessWithOkStatusAndEmptyResult() {

    }

    @Test
    void attemptToFindValidUsersByEmptyStringShouldEndUpWithResponseSuccessWithOkStatusAndResultListThatContainsAllUsersWithoutRemovedOnes() {

    }
}
