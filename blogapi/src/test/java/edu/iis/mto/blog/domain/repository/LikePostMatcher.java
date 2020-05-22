package edu.iis.mto.blog.domain.repository;

import edu.iis.mto.blog.domain.model.BlogPost;
import edu.iis.mto.blog.domain.model.LikePost;
import edu.iis.mto.blog.domain.model.User;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class LikePostMatcher extends TypeSafeMatcher<LikePost> {

    public static Matcher<LikePost> isLikedBy(User userWhoLikedPost) {
        return new LikePostMatcher(userWhoLikedPost);
    }

    public static Matcher<LikePost> hasContentSuchAs(BlogPost postToBeLiked) {
        return new LikePostMatcher(postToBeLiked);
    }

    private enum Mode{
        User,
        Blog
    }

    private Mode mode;
    private User userToMatch;
    private BlogPost blogToMatch;

    private LikePostMatcher(User user){
        mode = Mode.User;
        userToMatch = user;
    }

    private LikePostMatcher(BlogPost blog){
        blogToMatch = blog;
        mode = Mode.Blog;
    }

    @Override
    protected boolean matchesSafely(LikePost likePost) {
        switch (mode){
            case Blog:
                return blogToMatch.equals(likePost.getPost());
            case User:
                return userToMatch.equals(likePost.getUser());
            default:
                return false;
        }
    }

    @Override
    public void describeTo(Description description) {

    }
}
