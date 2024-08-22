package shop.mtcoding.blog2.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final HttpSession session;

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/board";
    }


    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/board";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        userRepository.save(joinDTO.toEntity());
        return "redirect:/login-form";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }
    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

}
