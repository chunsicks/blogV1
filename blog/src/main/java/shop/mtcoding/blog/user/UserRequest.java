package shop.mtcoding.blog.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

//요청 바디 데이터 받으려고 DTO만듬
public class UserRequest {
    //게터 세터 만들기
    @Data
    public static class JoinDTO {
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
        @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식으로 작성해주세요")
        @NotEmpty
        private String email;

        //컨버팅된다
        //빌더패턴 유저 new해주는 것
        //책임 -> DTO를 유저 오브잭트(엔티티)로 만들어줌
        public User toEntity() {
            return User.builder().username(username).password(password).email(email).build();
        }
    }

    @Data
    public static class LoginDTO {
        @NotEmpty
        @Size(min = 2, max = 4)
        private String username;
        @NotEmpty
        private String password;
    }
}
