package shop.mtcoding.blog4.board;


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
    public void findById_test(){
        int id = 1;
        Board board = boardRepository.findById(id);

        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(board.getContent()).isEqualTo("내용1");
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
