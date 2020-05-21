package edu.iis.mto.blog.domain.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

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

    @Before
    public void setUp() {
        
    }

    @Test
    public void queryingEmptyRepositoryShouldResultInEmptyListOfLikedPost() {
        var listOfLikedPost = repository.findAll();
        assertThat(listOfLikedPost, is(empty()));
    }
    
}
