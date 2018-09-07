package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Tag;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository <Tag, Integer> {
    public List<Tag> findAll();
    public Tag findById(int id);





}
