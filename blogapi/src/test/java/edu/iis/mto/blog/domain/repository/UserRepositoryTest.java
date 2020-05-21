package edu.iis.mto.blog.domain.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import edu.iis.mto.blog.domain.model.AccountStatus;
import edu.iis.mto.blog.domain.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setFirstName("Jan");
        user.setEmail("john@domain.com");
        user.setAccountStatus(AccountStatus.NEW);
    }

    @Test
    public void shouldFindNoUsersIfRepositoryIsEmpty() {
        List<User> users = repository.findAll();
        assertThat(users, hasSize(0));
    }

    @Test
    public void shouldFindOneUsersIfRepositoryContainsOneUserEntity() {
        User persistedUser = entityManager.persist(user);
        List<User> users = repository.findAll();
        assertThat(users, hasSize(1));
        assertThat(users.get(0)
                        .getEmail(),
                equalTo(persistedUser.getEmail()));
    }

    @Test
    public void shouldStoreANewUser() {
        User persistedUser = repository.save(user);
        assertThat(persistedUser.getId(), notNullValue());
    }

    @Test
    public void providingValidFirstNameToQueryRepositoryShouldReturnListOfUsersThatContainsGivenUser() {
        entityManager.persist(user);
        var listOfUsers = repository.findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("Jan", "", "");
        assertThat(listOfUsers, contains(user));
    }

    @Test
    public void providingValidPartOfFirstNameToQueryRepositoryShouldReturnListOfUsersThatContainsGivenUser() {
        entityManager.persist(user);
        var listOfUsers = repository.findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("an", "", "");
        assertThat(listOfUsers, contains(user));
    }

    @Test
    public void providingEmptyStringsToQueryRepositoryShouldReturnListOfUsersThatContainsGivenUser() {
        entityManager.persist(user);
        var listOfUsers = repository.findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("","", "");
        assertThat(listOfUsers, contains(user));
    }

}
