package shop.mtcoding.blog5.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardRepository boardRepository;

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id")int id, @RequestParam("title")String title, @RequestParam("content")String content) {
        boardRepository.updateById(id, title, content);
        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id) {
        boardRepository.deleteById(id);
        return "redirect:/board";
    }

    @PostMapping("/board/save")
    public String save(@RequestParam("title")String title, @RequestParam("content")String content) {
        boardRepository.save(title, content);
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String list(HttpServletRequest request){
        List<Board> boardList =boardRepository.findAll();
        request.setAttribute("models", boardList);
        return "board/list";
    }
    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request){
        Board board = boardRepository.findById(id);
        request.setAttribute("model", board);
        return "board/detail";
    }
    @GetMapping("/board/save-form")
    public String saveForm(){
        return "board/save-form";
    }
    @GetMapping("/board/{id}/update-form")
    public String updateFrom(@PathVariable int id, HttpServletRequest request){
        Board board = boardRepository.findById(id);
        request.setAttribute("model", board);
        return "board/update-form";
    }
}
