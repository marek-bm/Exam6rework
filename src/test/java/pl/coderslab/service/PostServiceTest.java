package pl.coderslab.service;

import org.junit.Before;
import org.junit.Test;
import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;
import pl.coderslab.repository.PostRepository;
import pl.coderslab.service.impl.PostServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class PostServiceTest {

    private PostService service;
    private PostRepository repository;

    @Before
    public void setUp(){
        repository=mock(PostRepository.class);
        service=new PostServiceImpl(repository);
    }

    @Test
    public void findAllByTagsName(){
        //given
        List<Post> posts=new ArrayList<>();

        Tag t1=new Tag("tag1");
        Tag t2=new Tag("tag2");
        Tag t3=new Tag("tag3");

        Post p1=new Post();
        p1.addTag(t1);
        posts.add(p1);

        Post p2=new Post();
        p1.addTag(t1);
        posts.add(p2);

        when(repository.findAllByTagsName("tag1")).thenReturn(posts);


        //when
        List<Post> actual=service.findAllByTagsName("tag1");

        //then
        assertThat(actual).containsExactly(p1, p2);


    }

}