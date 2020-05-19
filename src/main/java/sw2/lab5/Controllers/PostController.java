package sw2.lab5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sw2.lab5.Entity.Post;
import sw2.lab5.Repository.PostRepository;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("listaPosts")
    public String listarPosts(Model model){
        List<Post> listaPosts = postRepository.findAll();
        model.addAttribute("lista", listaPosts);

        return "Posts/ListaPosts";
    }
}
