package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.core.Hello;

@RequiredArgsConstructor
@Controller
public class UserController {

    /*
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession session;
     */
    //private final UserRepository userRepository;
    private final UserService userService;
    private final HttpSession session;


    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid UserRequest.LoginDTO loginDTO, Errors errors) {
        User sessionUser = userService.로그인(loginDTO);
        //null이 아니면 세션에 넣어준다
        // 리다이랙트 해도 살아있다
        System.out.println("12312323123213" + sessionUser.getUsername());
        // 이거는 세션 유저 담아야 해서 필요하다
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(@Valid UserRequest.JoinDTO joinDTO, Errors errors) {
        userService.회원가입(joinDTO);
        return "redirect:/login-form";
    }


    //주소에 user를 안 적는다 왜?
    /*
    앞에 user, 이나 이런 거 적는 이유
    1. 어디에 연결됐는지 알려고
    2. /user/* -> 인증이 필요해
        /board/* -> 인증이 필요해
        주소로 통일 할 수 있어서   인증을 위한 기능은 앞에 엔티티명 안 적는게 유리하다
        만약 적엇다면    /api/*라는 주소는 인증이 필요해 라고 설계한다
        로그인이 필요하면 주소 앞에 api넣고 로그인이 필요 없다면 api를 안 붙이면 된다.  개인적으로는 안 붙이는게 좋다
     */

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @Hello
    @GetMapping("/login-form")
    public String login() {
        System.out.println("loginFrom 호출됨");
        return "user/login-form";
    }

}
