package shop.mtcoding.blog2.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.lang.NonNull;
import shop.mtcoding.blog2.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "board_tb")
@Setter
@Getter
@Entity
public class Board {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @Builder
    public Board(Integer id, String title, String content, Timestamp createdAt, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }
}
