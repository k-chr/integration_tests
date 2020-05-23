package edu.iis.mto.blog.rest.test;

import org.junit.jupiter.api.Test;

public class FindPostsOfUserTest extends FunctionalTests {

    @Test
    void attemptToFindPostsOfRemovedUserShouldEndUpWithResponseErrorWithBadRequestStatus() {

    }

    @Test
    void attemptToFindPostsOfValidUserShouldEndUpWithResponseSuccessWithOkStatusAndLikedPostWithCorrectCountOfLikes() {

    }

    @Test
    void attemptToFindPostsOfNonExistingUserShouldEndUpWithResponseErrorWithNotFoundStatus() {

    }

    @Test
    void attemptToFindPostsOfValidUserWhoHasNoPostsShouldEndUpWithResponseSuccessWithOkStatusAndEmptyListOfPosts() {

    }

    @Test
    void attemptToFindPostsOfValidUserWhoHasPostsShouldEndUpWithResponseSuccessWithOkStatusAndListOfPostsWithCorrectCount() {

    }
}
