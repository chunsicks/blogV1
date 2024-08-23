package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.core.error.ex.Exception401;

@RequiredArgsConstructor
@Repository
public class UserRepository {


    private final EntityManager em;

    //유저이름 중복 체크
    public User findByUsername(String username) {
        Query query = em.createQuery("select u from User u where u.username=:username ", User.class);
        query.setParameter("username", username);

        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    //조회 커리
    public User findByUsernameAndPassword(String username, String password) {
        Query query = em.createQuery("select u from User u where u.username=:username and u.password=:password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            throw new Exception401("인증되지 않았습니다");
        }
    }

    //이 메서드에서 한 것 쿼리 없고 프라이머리키 빼고 넣어서 오토 인크리먼트 될거임  -> 알아서 insert함
    //빈 객체를 적시면 id없네 하고 insert함
    @Transactional
    public void save(User user) {
        //지금은 비영속 객체
        //프라이머리 키 뺀 이름, 비번, 이메일을 가지고 있다.
        //담기 전에는 heap에 있다
        System.out.println("담기기전 : " + user.getId());
        em.persist(user);
        //담긴 후 영속 객체다
        System.out.println("담긴 후 : " + user.getId());
    }
}
