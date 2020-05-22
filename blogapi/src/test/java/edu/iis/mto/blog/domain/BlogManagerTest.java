package edu.iis.mto.blog.domain;

import static edu.iis.mto.blog.domain.ExceptionMatcher.hasMessage;
import static edu.iis.mto.blog.domain.repository.LikePostMatcher.hasContentSuchAs;
import static edu.iis.mto.blog.domain.repository.LikePostMatcher.isLikedBy;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import edu.iis.mto.blog.domain.errors.DomainError;
import edu.iis.mto.blog.domain.model.BlogPost;
import edu.iis.mto.blog.domain.model.LikePost;
import edu.iis.mto.blog.domain.repository.BlogPostRepository;
import edu.iis.mto.blog.domain.repository.LikePostRepository;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import edu.iis.mto.blog.api.request.UserRequest;
import edu.iis.mto.blog.domain.model.AccountStatus;
import edu.iis.mto.blog.domain.model.User;
import edu.iis.mto.blog.domain.repository.UserRepository;
import edu.iis.mto.blog.mapper.BlogDataMapper;
import edu.iis.mto.blog.services.BlogService;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogManagerTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BlogPostRepository blogPostRepository;

    @MockBean
    private LikePostRepository likePostRepository;

    @Autowired
    private BlogDataMapper dataMapper;

    @Autowired
    private BlogService blogService;

    @Captor
    private ArgumentCaptor<User> userParam;

    private final long FAKE_USER_ID_1 = 0x01;
    private final long FAKE_USER_ID_2 = 0x02;
    private final long FAKE_POST_ID_1 = 0x03;

    private User owner;
    private User theManWhoLikes;
    private BlogPost post;

    @Before
    public void setUp()  {
        initEntities();
        initUser(owner, "Edward", "McDonald", "some_mail@example.com", AccountStatus.NEW, FAKE_USER_ID_1);
        initUser(theManWhoLikes, "Sigfrid", "Lehman", "sig@elpmaxe.com", AccountStatus.NEW, FAKE_USER_ID_2);
        initBlogPost(post, owner, "Example data", FAKE_POST_ID_1);
    }

    @Test
    public void creatingNewUserShouldSetAccountStatusToNEW() {
        blogService.createUser(new UserRequest("John", "Steward", "john@domain.com"));
        verify(userRepository).save(userParam.capture());
        User user = userParam.getValue();
        assertThat(user.getAccountStatus(), Matchers.equalTo(AccountStatus.NEW));
    }

    @Test
    public void ifUserWithoutConfirmedAccountStatusTriedToLikePostItShouldEndUpWithDomainErrorThrown() {
        when(userRepository.findById(FAKE_USER_ID_1)).thenReturn(Optional.of(owner));
        when(userRepository.findById(FAKE_USER_ID_2)).thenReturn(Optional.of(theManWhoLikes));
        when(blogPostRepository.findById(FAKE_POST_ID_1)).thenReturn(Optional.of(post));
        when(likePostRepository.findByUserAndPost(theManWhoLikes, post)).thenReturn(Optional.empty());

        var exception = assertThrows(DomainError.class, () -> blogService.addLikeToPost(FAKE_USER_ID_2, FAKE_POST_ID_1));
        assertThat(exception, hasMessage(DomainError.USER_NOT_CONFIRMED));
    }

    @Test
    public void ifConfirmedUserTriedToLikePostOfOtherUserRepositoryShouldSaveLike(){
        theManWhoLikes.setAccountStatus(AccountStatus.CONFIRMED);
        when(userRepository.findById(FAKE_USER_ID_1)).thenReturn(Optional.of(owner));
        when(userRepository.findById(FAKE_USER_ID_2)).thenReturn(Optional.of(theManWhoLikes));
        when(blogPostRepository.findById(FAKE_POST_ID_1)).thenReturn(Optional.of(post));
        when(likePostRepository.findByUserAndPost(theManWhoLikes, post)).thenReturn(Optional.empty());

        blogService.addLikeToPost(FAKE_USER_ID_2, FAKE_POST_ID_1);
        var likePostCaptor = ArgumentCaptor.forClass(LikePost.class);
        verify(likePostRepository).save(likePostCaptor.capture());
        var likePost = likePostCaptor.getValue();
        
        assertThat(likePost, isLikedBy(theManWhoLikes));
        assertThat(likePost, hasContentSuchAs(post));
    }

    @Test
    public void ifConfirmedUserTriedToLikeOwnPostItShouldEndUpWithDomainErrorThrown() {
        owner.setAccountStatus(AccountStatus.CONFIRMED);
        when(userRepository.findById(FAKE_USER_ID_1)).thenReturn(Optional.of(owner));
        when(blogPostRepository.findById(FAKE_POST_ID_1)).thenReturn(Optional.of(post));
        when(likePostRepository.findByUserAndPost(owner, post)).thenReturn(Optional.empty());

        var exception = assertThrows(DomainError.class, ()->blogService.addLikeToPost(FAKE_USER_ID_1, FAKE_POST_ID_1));
        assertThat(exception, hasMessage(DomainError.SELF_LIKE));
    }

    private void initEntities(){
        owner = new User();
        theManWhoLikes = new User();
        post = new BlogPost();
    }

    private void initUser(User user, String firstName, String lastName, String email, AccountStatus status, Long id) {
        user.setEmail(email);
        user.setAccountStatus(status);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setId(id);
    }

    private void initBlogPost(BlogPost post, User user, String data, Long id) {
        post.setUser(user);
        post.setEntry(data);
        post.setId(id);
    }
}
