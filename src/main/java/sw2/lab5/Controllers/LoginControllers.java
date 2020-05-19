package sw2.lab5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sw2.lab5.Entity.Usuario;
import sw2.lab5.Repository.UsusarioRepository;

import javax.servlet.http.HttpSession;

@Controller
public class LoginControllers {
    @Autowired
    UsusarioRepository ususarioRepository

    @GetMapping("/loginForm")
    public String loginForm(){
        return"inicio";
    }

    @GetMapping("/redirectByRole")
    public String redirigir(Authentication auth, HttpSession session){
        Usuario usuarioLogueado = ususarioRepository.findByEmail(auth.getName());
        session.setAttribute("user",usuarioLogueado);
        return "redirect:/post";
    }
}
