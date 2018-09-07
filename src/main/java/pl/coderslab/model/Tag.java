package pl.coderslab.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "tags")
public class Tag {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    private String name;

    @ManyToMany (cascade = CascadeType.MERGE, mappedBy = "tags")
    private List<Post> posts=new ArrayList<>();

    public void addPost(Post post){
        this.posts.add(post);
    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


    @Override
    public String toString() {
        return "Tag{" + "id=" + id + ", name='" + name + '\'' + ", posts=" + posts + '}';
    }
}
