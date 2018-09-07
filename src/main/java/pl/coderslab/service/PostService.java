package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;

import java.util.List;
import java.util.Set;


public interface PostService {

    List<Post> findAllByTags(Tag tag);
    List<Post> findAllByTagsName(String tagName);
    List<Post> findPostsByTagsIn(Set<Tag> tags);
    void save(Post post);


}
