package shop.mtcoding.blog.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(UserRepository.class)
public class UserReopsitoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save_test() {
        String username = "haha";
        String password = "1234";
        String email = "haha@gmail.com";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        userRepository.save(user.getUsername(), user.getPassword(), user.getEmail());
    }
}
