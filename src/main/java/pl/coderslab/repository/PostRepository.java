package pl.coderslab.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByTags(Tag tag);
    List<Post> findAllByTagsName(String tagName);
    List<Post> findPostsByTagsIn(Set<Tag> tags);
    Post save(Post post);

}
