package shop.mtcoding.blog.user;

import lombok.Data;

//요청 바디 데이터 받으려고 DTO만듬
public class UserRequest {
    //게터 세터 만들기
    @Data
    public static class JoinDTO {
        private String username;
        private String password;
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
        private String username;
        private String password;
    }
}
