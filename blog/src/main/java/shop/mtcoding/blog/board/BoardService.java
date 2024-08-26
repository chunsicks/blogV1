package shop.mtcoding.blog.board;

//지금
//  C -> S -> R


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.core.error.ex.Exception403;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void 게시글수정(int id, BoardRequest.UpdateDTO updateDTO, User sessionUser) {
        //1. 조회(없으면 404)
        Board board = boardRepository.findById(id);
        //2. 권한 체크
        if (board.getUser().getId() != sessionUser.getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }
        //3. 게시글 수정(여기서 update 쿼리 해도 됨
        //flush() 자동 호출됨(더티 체킹)
        board.setTitle(updateDTO.getTitle());
        board.setContent(updateDTO.getContent());
    }

    //권한체크 반드시 해야 해서 조회만 한다고 끝나지 않음
    public Board 게시글수정화면가기(int id, User sessionUser) {
        //일단 조회를 해!   만약 터트리는 거를 null로 설정 햇으면 따로 해줘야 함
        Board board = boardRepository.findById(id);
        if (board.getUser().getId() != sessionUser.getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }
        return board;
    }

    public List<Board> 게시글목록보기() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    @Transactional
    public void 게시글삭제(int id, User sessionUser) {
        Board board = boardRepository.findById(id);
        // 게시글이 있는가? 이거는 findById에서 알아서 throw해준다!
        // 2. 내가 쓴 글인가?
        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("본인이 작성한 글이 아닙니다");
        }
        // 정상 로직 실행
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO, User sessionUser) {
        //누가 적었는지 알아야 해서 세션 넣어야 함
        boardRepository.save(saveDTO.toEntity(sessionUser));
        //게시글 인증이 여기서 해야 할까? 안 해도 됨 DB안 가도 되니까 컨트롤러에서 해도 욈
    }

    //기능명을 한글로적는다 정확해야해서  그냥 마음대로 해도 됨
    //조인해서 보드안에 User포함돼 있다.  이것만 리턴하면 끝인가?
    // 1. 보드가 널인가? 이미 처리해줌  비지니스로직 꼼꼼하게 해야
    // 2. 이 게시글에 get유저 가 session(로그인 한 사람)과 같은지 지금 세션 정보 없으니까 User sessionUser 컨트롤러한테 받아오자
    //같으면 주인 아니면 주인X

    // return board; 하면 isOwner못 돌려주는 억울한 일이 생긴다  board, isOwner둘 다 받아야 한다  그래서 DTO를 만든다!
    //이유 2. 레이지 로딩 끌거니까
    // 3. 뭐였지
    public BoardResponse.DetailDTO 상세보기(int id, User sessionUser) {
        Board board = boardRepository.findById(id);
        return new BoardResponse.DetailDTO(board, sessionUser);
    }
}