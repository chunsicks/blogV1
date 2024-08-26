package shop.mtcoding.blog.board;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardRequest {

    @Data
    public static class UpdateDTO {
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
        //포린키 받아야 하는데 클라이언트는 모름 세션에서 꺼내와야 한다 세션에 id있어서
        //퍼시스트 할거다  보드 오브젝트로 해야함

    }

    @Data
    public static class SaveDTO {

        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
        //포린키 받아야 하는데 클라이언트는 모름 세션에서 꺼내와야 한다 세션에 id있어서
        //퍼시스트 할거다  보드 오브젝트로 해야함

        public Board toEntity(User sessionUser) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(sessionUser)
                    .build();
        }
    }
}
