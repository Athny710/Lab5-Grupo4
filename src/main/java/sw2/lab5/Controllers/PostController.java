package sw2.lab5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.lab5.Entity.Post;
import sw2.lab5.Repository.PostRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("listaPosts")
    public String listarPosts(Model model) {
        List<Post> listaPosts = postRepository.findAll();
        model.addAttribute("lista", listaPosts);

        return "Posts/ListaPosts";
    }

    @GetMapping("borrarPosts")
    public String borrarPosts(@RequestParam("id") int id, RedirectAttributes attr) {
        Optional<Post> opt = postRepository.findById(id);
        if (opt.isPresent()) {
            postRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Post Borrado Exitosamente!");
        }
        return "redirect:/posts/listaPosts";
    }

    @GetMapping("nuevoPost")
    public String nuevoPost(@ModelAttribute("post") Post post) {
        return "Posts/FormPosts";
    }

    @GetMapping("editarPost")
    public String editarPost(@ModelAttribute("post") Post post,
                             Model model, @RequestParam("id") int id) {
        Optional<Post> opt = postRepository.findById(id);
        if (opt.isPresent()) {
            return "Posts/FormPost";
        } else {
            return "redirect:/posts/listaPosts";
        }
    }

    public String guardarPost(@ModelAttribute("post") @Valid Post post,
                              BindingResult bindingResult,
                              Model model, RedirectAttributes attr){
        if(bindingResult.hasErrors()){
            return "Postss/FormPost";
        }else{
            attr.addFlashAttribute("msg", "Post"+(post.getId_post()==0 ? "creado" : "actualizado")+"exitósamente!");
            postRepository.save(post);
            return "redirect:/post/listaPosts";
        }
    }
}
