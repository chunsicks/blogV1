package shop.mtcoding.blog2.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog2.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {


    private final BoardRepository boardRepository;
    private final HttpSession session;

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") int id, @RequestParam("title") String title,@RequestParam("content") String content) {
        boardRepository.updateById(title, content, id);
        return "redirect:/board/" + id;

    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id")int id) {
        boardRepository.deleteById(id);
        return "redirect:/board";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardRepository.save(saveDTO.toEntity(sessionUser));
        return "redirect:/board";

    }

    @GetMapping("/board")
    public String list(HttpServletRequest request) {
        List<Board> boardList = boardRepository.findAll();
       request.setAttribute("models", boardList);
        return "board/list";//파일의 경로 넣으면 되는데 고정적으로 되어 있음  확장자 자동으로 해주는가? 라이브러리로 머스테치 설정해줘서(templates에 머스테치로 찾아줌)
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") int id, HttpServletRequest request) {
        Board board = boardRepository.findById(id);
        request.setAttribute("model", board);
        return"/board/detail";
    }
    @GetMapping("/board/save-form")
    public String saveForm(){
        return"/board/save-form";
    }
    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request){
        Board board = boardRepository.findById(id);
        request.setAttribute("model", board);
        return"/board/update-form";
    }

}
