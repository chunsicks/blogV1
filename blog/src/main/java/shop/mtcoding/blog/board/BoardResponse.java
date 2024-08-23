package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardResponse {
    //보드 응답 DTO  상세보기
    @Data
    public static class DetailDTO {
        private Integer boardId;
        private String title;
        private String content;
        private Boolean isOwner;
        private Integer userId;
        private String username;

        //가장 중요한 것 entitiy 총 2개 board, user
        //프라이머리키는 반드시 줘야해 프론트가 뭘 할 수도 있으니까


        public DetailDTO(Board board, User sessionUser) {
            this.boardId = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.isOwner = false;

            if (board.getUser().getId() == sessionUser.getId()) {
                isOwner = true;
            }

            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
        }
    }

    @Data
    public static class DetailDTOV2 {
        private Integer id;
        private String title;
        private String content;
        private Boolean isOwner;
        private UserDTO user;

        public DetailDTOV2(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.isOwner = false;

            if (board.getUser().getId() == sessionUser.getId()) {
                isOwner = true;
            }
            this.user = new UserDTO(board.getUser());
        }

        @Data
        public class UserDTO {
            private Integer id;
            private String username;

            public UserDTO(User user) {
                this.id = user.getId();
                this.username = user.getUsername();
            }
        }

    }
}
