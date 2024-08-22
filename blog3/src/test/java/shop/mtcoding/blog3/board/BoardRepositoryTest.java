package shop.mtcoding.blog3.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(BoardRepository.class)
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void deleteById_test(){
        int id = 1;
        boardRepository.deleteById(1);
    }

    @Test
    public void findById_test(){
        int id = 1;

        Board board = boardRepository.findById(id);
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }

    @Test
    public void findAll_test(){
        boardRepository.findAll();
    }

    @Test
    public void save_test(){
        String title = "제목1";
        String content = "내용1";

        boardRepository.save(title, content);
    }
}
