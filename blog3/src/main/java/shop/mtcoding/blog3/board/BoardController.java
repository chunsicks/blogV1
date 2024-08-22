package shop.mtcoding.blog3.board;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;


    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        boardRepository.deleteById(id);
        return "redirect:/board";
    }

    @PostMapping("/board/save")
    public String save(String title, String content) {
        boardRepository.save(title, content);
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String list(HttpServletRequest request){
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("models", boardList);
        return "board/list";
    }
    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request){
        Board board = boardRepository.findById(id);
        request.setAttribute("model", board);
        return "board/detail";
    }
    @GetMapping("/board/save-form")
    public String saveForm(){
        return "board/save-form";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable int id, HttpServletRequest request){
        Board board = boardRepository.findById(id);
        request.setAttribute("model", board);
        return "board/update-form";
    }
}
