package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;
import pl.coderslab.repository.PostRepository;
import pl.coderslab.service.PostService;

import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAllByTags(Tag tag) {
        return postRepository.findAllByTags(tag);
    }

    @Override
    public List<Post> findAllByTagsName(String tagName) {
        return postRepository.findAllByTagsName(tagName);
    }

    @Override
    public List<Post> findPostsByTagsIn(Set<Tag> tags) {
        return postRepository.findPostsByTagsIn(tags);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }


    public Post addPosts(List<Post> posts) {
        return (Post) postRepository.save(posts);
    }

}
