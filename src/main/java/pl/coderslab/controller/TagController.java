package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;
import pl.coderslab.service.PostService;
import pl.coderslab.service.TagService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @Autowired
    PostService postService;

    @RequestMapping (value = "/add", method = RequestMethod.GET)
    public String addTag(Model model){
        Tag tag=new Tag();
        model.addAttribute("tag", tag);
        return "addTag";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveTag(@Valid Tag tag, BindingResult result){
        if(result.hasErrors()){
            return ("/tag/add");
        }

        tagService.saveTag(tag);
        return "redirect:/tag/all";
    }


    @RequestMapping("/all")
    public String allTags(Model model){
        List<Tag> tags = new ArrayList<>();
        tags=tagService.findAll();
        model.addAttribute("tags", tags);
        return "allTags";
    }


    @RequestMapping(value = "/{id}/posts")
    public String allPostByTag(@PathVariable Integer id, Model model){
        Tag tag=tagService.findById(id);
        List<Post> posts=postService.findAllByTags(tag);
        model.addAttribute("posts", posts);
        model.addAttribute("tag", tag);

        return "posts-by-tag";

    }

    @RequestMapping ("/{id}/show")
    public String showTag(@PathVariable Integer id, Model model){
        Tag tag=tagService.findById(id);
        model.addAttribute("tag", tag);
        return "showTag";
    }

}
