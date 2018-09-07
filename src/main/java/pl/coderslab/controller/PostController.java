package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;
import pl.coderslab.service.PostService;
import pl.coderslab.service.TagService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    TagService tagService;

    @Autowired
    PostService postService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPost(Model model){

        Post post=new Post();
        model.addAttribute("post", post);

        List<Tag> tags=tagService.findAll();
        model.addAttribute("tags", tags);
        return "addPost";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String savePost(@Valid Post post, BindingResult result){
        if (result.hasErrors()){
            return "/post/add";
        }

        int i=post.getTags().size();
        String s=post.getTags().get(0).toString();

        postService.save(post);
        return "redirect:/panel";
    }

}
