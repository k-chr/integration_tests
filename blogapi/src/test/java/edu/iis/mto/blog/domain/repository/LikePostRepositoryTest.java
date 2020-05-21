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
        initUsers();
        initUser(userWithPost, "Jack", "White", "jack_white@thirdmanrecords.com", AccountStatus.NEW);
        initUser(userWhoLikedPost, "Bruce", "Willis", "die_hard@gmail.com", AccountStatus.NEW);
        initUser(userWhoHadNotAnyPostsAndDidNotLikeAnyOthers, "Anonymous", "Nameless", "anon@o2.pl", AccountStatus.NEW);

    }


    @Test
    public void queryingEmptyRepositoryShouldResultInEmptyListOfLikedPost() {
        var listOfLikedPost = repository.findAll();
        assertThat(listOfLikedPost, is(empty()));
    }

    private void initUsers() {
        userWithPost = new User();
        userWhoHadNotAnyPostsAndDidNotLikeAnyOthers = new User();
        userWhoLikedPost = new User();
    }

    private void initUser(User user, String firstName, String lastName, String email, AccountStatus status) {
        user.setEmail(email);
        user.setAccountStatus(status);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);
    }


}
