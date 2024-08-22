package shop.mtcoding.blog2.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    public User findByUsernameAndPassword(String username, String password) {
        Query query = em.createQuery("select u from User u where u.username=:username and u.password=:password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        User user = (User) query.getSingleResult();
        return user;
    }

    // 유저의 save는 뭐 하는거지? 그냥 값 넎으려고 하는건가?
    public void save(User user) {
        em.persist(user);
    }
}
