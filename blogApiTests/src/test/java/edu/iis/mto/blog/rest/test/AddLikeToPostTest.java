package edu.iis.mto.blog.rest.test;

import org.junit.jupiter.api.Test;

public class AddLikeToPostTest extends FunctionalTests {

    @Test
    void attemptToAddLikeToPostByUserThatHasNewAccountShouldEndUpWithResponseErrorWithBadRequestStatus() {

    }

    @Test
    void attemptToAddLikeToPostByUserThatBeforeAddedLikeToThatPostShouldEndUpWithNoChangeInLikesCountOfThatPost() {

    }

    @Test
    void attemptToAddLikeToPostByUserWhoCreatedThatPostShouldEndUpWithResponseErrorWithBadRequestStatus() {

    }

    @Test
    void attemptToAddLikeToPostByUserThatHasRemovedAccountShouldEndUpWithResponseErrorWithBadRequestStatus() {

    }

    @Test
    void attemptToAddLikeToPostByUserThatNotExistsShouldEndUpWithResponseErrorWithNotFoundStatus() {

    }

    @Test
    void attemptToAddLikeToPostByUserThatHasConfirmedAccountAndIsNotOwnerOfPostShouldEndUpWithResponseSuccessWithCreatedStatus() {

    }

    @Test
    void attemptToAddLikeToPostByAnotherConfirmedUserShouldEndUpWithChangeInLikesCountOfThatPost() {

    }
}
