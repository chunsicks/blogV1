package shop.mtcoding.blog2.board;

import jakarta.persistence.Table;
import org.assertj.core.api.Assertions;
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
        int id = 1;
        String title = "제목1변경";
        String content = "내용1변경";

        boardRepository.updateById(title, content, id);

        Board board = boardRepository.findById(id);
    }

    @Test
    public void deleteById_test(){
        int id = 1;

        boardRepository.deleteById(id);
        try {
            boardRepository.findById(id);
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("게시글 id를 찾을 수 없습니다");
        }
    }

    @Test
    public void findById_test(){
        int id = 6;
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

        //boardRepository.save(title,content);
    }
}
