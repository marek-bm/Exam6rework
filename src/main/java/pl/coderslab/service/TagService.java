package pl.coderslab.service;

import pl.coderslab.model.Tag;

import java.util.List;

public interface TagService {

    void saveTag(Tag tag);
    public List<Tag> findAll();
    public Tag findById(int id);

}
