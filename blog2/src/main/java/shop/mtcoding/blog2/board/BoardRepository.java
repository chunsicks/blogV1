package shop.mtcoding.blog2.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BoardRepository {
    @Autowired
    private EntityManager em;

    @Transactional
    public void updateById(String title, String content, int id){
        Query query = em.createNativeQuery("update board_tb set title=?, content=? where id=?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, id);
        query.executeUpdate();
    }

    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id =?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    public Board findById(int id){
        Query query = em.createQuery("select b from Board b join fetch b.user U where b.id =:id", Board.class);
        query.setParameter("id", id);
        try {
            Board board = (Board) query.getSingleResult();
            return board;
        } catch (Exception e) {
            throw new RuntimeException("게시글 id를 찾을 수 없습니다");
        }
    }

    public List<Board> findAll(){
        Query query = em.createQuery("select b from Board b order by b.id desc ", Board.class);
        List<Board> boardList = query.getResultList();
        return boardList;
    }

    @Transactional
    public void save(Board board) {
      /*
        Query query = em.createNativeQuery("insert into board_tb (title, content, created_at) values (?, ?, now())");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.executeUpdate();

       */
        em.persist(board);
    }
}
