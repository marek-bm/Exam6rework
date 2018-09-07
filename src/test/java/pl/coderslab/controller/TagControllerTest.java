package pl.coderslab.controller;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.coderslab.model.Tag;
import pl.coderslab.service.TagService;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TagController.class)
public class TagControllerTest {
    private MockMvc mockMvc;

    private static String ALL_TAGS_PAGE_VIEW_NAME="allTags";
    private static String SHOW_TAG="showTag";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    TagService tagService;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        List<Tag> tags= Arrays.asList(new Tag("tag1"), new Tag("tag2"), new Tag("tag3"));
        when(this.tagService.findAll()).thenReturn(tags);

        Tag tag=new Tag();
        tag.setName("Java");
        when(this.tagService.findById(tag.getId()).getName()).thenReturn("Java");
    }

/*
BLAD:Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException:
Error creating bean with name 'tagController':
Unsatisfied dependency expressed through field 'postService'; nested exception is org.springframework.beans.factory.
NoSuchBeanDefinitionException:
No qualifying bean of type 'pl.coderslab.service.PostService' available: expected at least 1 bean which qualifies as autowire candidate.
 */

    @Test
    public void allTags() throws Exception {
        mockMvc.perform(get("/tag/all"))
                .andExpect(model().attributeExists("tags"))
                .andExpect(model().attribute("tags",hasSize(3)))
                .andExpect(model().attribute ("tags", hasItem(CoreMatchers.anyOf(hasProperty("name"), is("tag1")))))
                .andExpect(status().isOk())
                .andExpect(view().name(ALL_TAGS_PAGE_VIEW_NAME));
    }

    @Test
    public void showTag() throws Exception {
        mockMvc.perform(get("/tag/{id}/show", 1))
                .andExpect(status().isOk())
                .andExpect(view().name(SHOW_TAG))
                .andExpect(model().attribute("tag", hasProperty("name", is ("Java"))));

    }

    public void addTagDataCorreect() throws Exception {
        mockMvc.perform(post("/tag/add").param("name", "Python"))
                .andExpect(redirectedUrl("/tag/all"));

    }

    public void addTagDataWrong() throws Exception {
        mockMvc.perform(post("/tag/add").param("name", ""))
                .andExpect(view().name("/tag/add"));
    }
}