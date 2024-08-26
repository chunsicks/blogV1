package shop.mtcoding.blog.core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.mtcoding.blog.core.error.ex.Exception401;
import shop.mtcoding.blog.user.User;

//타입은 HandlerInterceptro다
public class Logininterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        //System.out.println("프리핸들 동작합 ---------------------------");
        if (sessionUser == null) {
            /*
            //어떤 텍스튼지 몰라서  아래 줄 적어줘야 함
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.print("<script> alert('인증되지 않았엉요');\n");
            pw.print("location.href='login-form';\n");
            //버퍼에 담았으니 flush()래줘야 한다
            //엔터키로 받는데 파싱할 때 한즐로 받아서 몰라서 무조건 \n을 적어줘야 한다!! 안적으면 뭔지 몰라서 파싱 못함!!!
            //기본이 flush와 \n이다!!
            //프린터 라이터 좋은 점은 println으로 자동으로 해주고 , 버퍼드 라이터는 플러시 알아서 해줘서 안 적어도 된다!!
            //
            pw.flush();

             */
            throw new Exception401("인증되지 않았어요");
        }
        return true;
    }
}
