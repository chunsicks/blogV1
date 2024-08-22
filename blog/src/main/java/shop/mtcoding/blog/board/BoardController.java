package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.user.User;

import java.util.List;

//식별자 요청 받기, 응답하기가 이 친구의 책임

//1. 이거 안붙이면 식별자 요청 못 받는다
@RequiredArgsConstructor
@Controller  //2. 식별자 요청을 받을 수 있다 이런거를 어노테이션이라 한다 왜 식별자 요청이 되는가 이거는 나중에 설명해준다
public class BoardController {

    private final BoardRepository boardRepository;

    private final HttpSession session;
    private final BoardService boardService;


    @GetMapping("/test/board/1")
    public @ResponseBody void testBoard() {
        //여기까지는 레이지 유저 안 땡겨옴
        List<Board> boardList = boardRepository.findAll();
        System.out.println("---------------------------------");
        //보드 리스트를 리턴해버리면? getter다 때려서 json로 바꿔야 함
        System.out.println(boardList.get(2).getUser().getPassword());
        System.out.println("---------------------------------------------");
    }

    // url : http://localhost:8080/board/1/update
    //바디 데이터가 title=제목1 변경&content=내용1변경
    //content-type : x-www-form-urlencoded
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") int id, @RequestParam("title") String title, @RequestParam("content") String content) {
        boardRepository.updateById(title, content, id);
        //상세보기로 가야함
        return "redirect:/board/" + id;
    }


    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        //원래는 조회를 하고 삭제해야하는데 V1이라 그냥 함
        boardRepository.deleteById(id);
        return "redirect:/board";
    }


    //글쓰기 할게 -> 글쓰기 완료되면 메인으로 보내는게 좋다

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) { //스프링 기본 견략 = x-www-form-urlencoded  파싱   매개변수만 같으면 됨   화면에 폼테그 name 과 반드시 같아야 한다!!
        //세션유저가 null이면 인증 안됨 null아니면 인증됨
        User sessionUser = (User) session.getAttribute("sessionUser");

        //인증 체크 필요함
        if (sessionUser == null) {
            throw new RuntimeException("로그인이 필요합니다");
        }

        boardRepository.save(saveDTO.toEntity(sessionUser));  //보드 레파지토리 객체가 어디있나?  Ioc에 있다 autoWrid해서 가져옴
        return "redirect:/board";
    }

    /*
  외부에서 이 메서드 어떻게 때리냐면
  방법 4가지   get(가지고 오고 요청할 때), post(보내고 insert, delete update 요청할 때), put, delete(지울때)
   */
    //리플랙션은 메서드 이름 필요 없다 주소만 필요하지
    @GetMapping("/board")
    public String list(HttpServletRequest request) {
        List<Board> boardList = boardRepository.findAll();
        //key값 모델스
        //외부에서 /board요청 -> 톰캣에 감 rqeust객체로 만들어둠 -> 때림 -> Model에 rqeust객체 주입됨 -> 이 데이터를 reqeust객체에 넣어버림
        request.setAttribute("models", boardList);
        return "board/list";//파일의 경로 넣으면 되는데 고정적으로 되어 있음  확장자 자동으로 해주는가? 라이브러리로 머스테치 설정해줘서(templates에 머스테치로 찾아줌)
    }

        /*
    1. 메서드 : get을 사용
    2. 주소 : /board/1
    3. 응답 : board/detail

    1. 메서드 : get을 사용
    2. 주소 : /board/1/save-form
    3. 응답 : board/save-form

    1. 메서드 : get을 사용
    2. 주소 : /board/1/update-form
    3. 응답 : board/update-form
     */

    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        //Board board = boardRepository.findById(id);
        //한건이니까 model
        //여러건이면 models
        //  request.setAttribute("model", board);
        // request.setAttribute("isOwner", false);
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO detailDTO = boardService.상세보기(id, sessionUser);
        request.setAttribute("model", detailDTO);

        return "board/detail";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        //못찾으면 터진다! null 안들어옴 우리가 설정해서
        Board board = boardRepository.findById(id);
        //리퀘스트에 담는다
        request.setAttribute("model", board);
        return "board/update-form";
    }
}
