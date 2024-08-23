package shop.mtcoding.blog5.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;
    @Transactional
    public void updateById(int id, String title, String content) {
        Query query = em.createNativeQuery("update board_tb set title=?, content=? where id = ?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, id);
        query.executeUpdate();
    }
    @Transactional
    public void deleteById(int id){
        Query query = em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    public Board findById(int id){
        Query query = em.createNativeQuery("select * from board_tb where id = ?", Board.class );
        query.setParameter(1, id);
        query.getSingleResult();
        Board board = (Board) query.getSingleResult();
        return board;
    }

    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        List<Board> boardList = query.getResultList();
        return boardList;
    }

    @Transactional
    public void save(String title, String content) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, created_at) values (?,?,now())");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.executeUpdate();
    }
}
