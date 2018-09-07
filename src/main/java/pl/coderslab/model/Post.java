package pl.coderslab.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name="posts")
public class Post {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min=5, max=100)
    private String title;

    private String content;

    @ManyToMany (cascade = CascadeType.MERGE )
    private List<Tag> tags= new ArrayList<>();

    public void addTag(Tag tag){
        this.tags.add(tag);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
