package shop.mtcoding.blog2.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Table(name = "user_tb")
@NoArgsConstructor
@Data
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    //유니크 해야함
    @Column(unique = true, nullable = false)
    private String username; //아이디
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @CreationTimestamp
    private Timestamp createdAt;


    

    @Builder
    public User(Integer id, String username, String password, String email, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }
}
