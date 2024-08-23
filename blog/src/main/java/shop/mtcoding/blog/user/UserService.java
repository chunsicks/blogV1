package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.core.error.ex.Exception400;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        //만약 잘 됐다 생각하고 만들자
        User oldUser = userRepository.findByUsername(joinDTO.getUsername());

        if (oldUser != null) {
            throw new Exception400("이미 존재하는 유저네임입니다");
        }
        userRepository.save(joinDTO.toEntity());
        //원래는 비번을 해시화 시켜서 암호화 해야함 아직은 안할거임
    }

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        //조회 안 되면 noresultException터짐 이거를 401로 잡아야 한다
        User user = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        return user;
    }
}
