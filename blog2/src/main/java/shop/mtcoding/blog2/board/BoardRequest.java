package shop.mtcoding.blog2.board;

import lombok.Data;
import shop.mtcoding.blog2.user.User;

public class BoardRequest {
    @Data
    public static class SaveDTO{
        private String title;
        private String content;

        public Board toEntity(User sessionUser){
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(sessionUser)
                    .build();
        }
    }
}
