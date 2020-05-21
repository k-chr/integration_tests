package edu.iis.mto.blog.domain.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import edu.iis.mto.blog.domain.model.AccountStatus;
import edu.iis.mto.blog.domain.model.BlogPost;
import edu.iis.mto.blog.domain.model.LikePost;
import edu.iis.mto.blog.domain.model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LikePostRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LikePostRepository repository;

    @Autowired
    private UserRepository userRepository;

    private User userWithPost;
    private User userWhoLikedPost;
    private User userWhoHadNotAnyPostsAndDidNotLikeAnyOthers;

    private BlogPost postToBeLiked;

    private LikePost likedPost;

    @Before
    public void setUp() {
        initEntities();
        initUser(userWithPost, "Jack", "White", "jack_white@thirdmanrecords.com", AccountStatus.NEW);
        initUser(userWhoLikedPost, "Bruce", "Willis", "die_hard@gmail.com", AccountStatus.NEW);
        initUser(userWhoHadNotAnyPostsAndDidNotLikeAnyOthers, "Anonymous", "Nameless", "anon@o2.pl", AccountStatus.NEW);
        initBlogPost(postToBeLiked, userWithPost, "template data");
        initLikePost(likedPost, userWhoLikedPost, postToBeLiked);
    }

    @Test
    public void queryingEmptyRepositoryShouldResultInEmptyListOfLikedPost() {
        var listOfLikedPost = repository.findAll();
        assertThat(listOfLikedPost, is(empty()));
    }

    @Test
    public void queryingNotEmptyRepositoryShouldResultInNotEmptyListOfLikedPosts() {
        repository.save(likedPost);
        var listOfLikedPosts = repository.findAll();
        assertThat(listOfLikedPosts, is(not(empty())));
    }

    private void initEntities() {
        userWithPost = new User();
        userWhoHadNotAnyPostsAndDidNotLikeAnyOthers = new User();
        userWhoLikedPost = new User();

        likedPost = new LikePost();

        postToBeLiked = new BlogPost();
    }

    private void initUser(User user, String firstName, String lastName, String email, AccountStatus status) {
        user.setEmail(email);
        user.setAccountStatus(status);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);
    }

    private void initBlogPost(BlogPost post, User user, String data) {
        post.setUser(user);
        post.setEntry(data);
        entityManager.persist(post);
    }

    private void initLikePost(LikePost likedPost, User user, BlogPost blogPost) {
        likedPost.setUser(user);
        likedPost.setPost(blogPost);
    }
}
