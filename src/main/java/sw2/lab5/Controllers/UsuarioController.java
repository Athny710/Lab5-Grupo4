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
import sw2.lab5.Entity.Usuario;
import sw2.lab5.Repository.PostRepository;
import sw2.lab5.Repository.UsusarioRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    UsusarioRepository ususarioRepository;

    @GetMapping(value = {"", "/", "list"})
    public String listarPosts(Model model) {
        List<Usuario> listaUsuarios = ususarioRepository.findAll();
        model.addAttribute("lista", listaUsuarios);

        return "usuario/listaUsuario";
    }

    @GetMapping("borrarPosts")
    public String borrarPosts(@RequestParam("id") int id, RedirectAttributes attr) {
        Optional<Usuario> opt = ususarioRepository.findById(id);
        if (opt.isPresent()) {
            ususarioRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Usuario Borrado Exitosamente!");
        }
        return "redirect:/posts/listaPosts";
    }


    @GetMapping("editarUsuario")
    public String editarPost(@ModelAttribute("post") Post post,
                             Model model, @RequestParam("id") int id) {
        Optional<Usuario> opt = ususarioRepository.findById(id);
        if (opt.isPresent()) {
            return "usuario/editUsuario";
        } else {
            return "redirect:/user/list";
        }
    }

    public String guardarUser(@ModelAttribute("user") @Valid Usuario usuario,
                              BindingResult bindingResult,
                              Model model, RedirectAttributes attr){
        if(bindingResult.hasErrors()){
            return "usuario/editarUsuario";
        }else{
            attr.addFlashAttribute("msg", "Usuario"+(usuario.getId_user()==0 ? "creado" : "actualizado")+"exitosamente!");
            ususarioRepository.save(usuario);
            return "redirect:/usuario/list";
        }
    }
}
