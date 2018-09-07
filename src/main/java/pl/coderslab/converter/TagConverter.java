package pl.coderslab.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.model.Tag;
import pl.coderslab.service.TagService;


@Component
public class TagConverter implements Converter<String, Tag> {
    @Autowired
    TagService tagService;

    @Override
    public Tag convert(String id) {
        Tag tag=tagService.findById(Integer.parseInt(id));
        return tag;
    }
}
