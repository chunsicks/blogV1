package shop.mtcoding.blog5.board;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import(BoardRepository.class)
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void updateById_test(){
        String title="제목1변경";
        String content ="내용1변경";
        int id = 1;
        boardRepository.updateById(id, title, content);
        Board board = boardRepository.findById(id);
        System.out.println(board.getTitle());
        System.out.println(board.getContent());

    }

    @Test
    public void deleteById_test(){
        int id = 1;
        boardRepository.deleteById(id);
        boardRepository.findById(id);
    }


    //아이디로 찾기
    @Test
    public void findById_test(){
        int id = 1;
        Board board = boardRepository.findById(id);
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }

    @Test
    public void findAll_test(){
        List<Board> boards = boardRepository.findAll();
        System.out.println(boards.size());
    }

    @Test
    public void save_test(){
        String title ="제목1";
        String content = "내용1";
        boardRepository.save(title,content);
    }
}
