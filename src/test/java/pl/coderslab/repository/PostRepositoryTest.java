package pl.coderslab.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    PostRepository postRepository;

//NIE DZIALA
//Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'tagRepository':
// Invocation of init method failed; nested exception is java.lang.IllegalArgumentException:
// Not a managed type: class pl.coderslab.model.Tag

    @Test
    public void findPostsByTagsIn() {
        //given
        Tag t1=new Tag("tag1");
        entityManager.persist(t1);
        Tag t2=new Tag("tag2");
        entityManager.persist(t2);
        Tag t3=new Tag("tag3");
        entityManager.persist(t3);

        Post p1=new Post();
        p1.addTag(t1);
        p1.addTag(t2);
        entityManager.persist(p1);

        Post p2=new Post();
        p1.addTag(t3);
        entityManager.persist(p2);

        Post p3=new Post();
        p3.addTag(t1);
        p3.addTag(t2);
        entityManager.persist(p3);

        Set<Tag> tags= new HashSet<>();
        tags.add(t1);
        tags.add(t2);

        //when
        List<Post> post=postRepository.findPostsByTagsIn(tags);

        //then
        assertThat(post).containsExactly(p1,p3);

    }
}