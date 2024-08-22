package shop.mtcoding.blog.board;

//지금
//  C -> S -> R


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    //기능명을 한글로적는다 정확해야해서  그냥 마음대로 해도 됨
    public BoardResponse.DetailDTO 상세보기(int id, User sessionUser) {
        Board board = boardRepository.findById(id); //조인해서 보드안에 User포함돼 있다.  이것만 리턴하면 끝인가? 1. 보드가 널인가? 이미 처리해줌  비지니스로직 꼼꼼하게 해야
        //2. 이 게시글에 get유저 가 session(로그인 한 사람)과 같은지 지금 세션 정보 없으니까 User sesswionUser 컨트롤러한테 받아오자
        //같으면 주인 아니면 주인X

        // return board; 하면 isOwner못 돌려주는 억울한 일이 생긴다  board, isOwner둘 다 받아야 한다  그래서 DTO를 만든다!
        //이유 2. 레이지 로딩 끌거니까
        // 3. 뭐였지
        return new BoardResponse.DetailDTO(board, sessionUser);
    }
}