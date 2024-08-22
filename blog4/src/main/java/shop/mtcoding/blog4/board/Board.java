package shop.mtcoding.blog4.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "board_tb")
@Data
@Entity
public class Board {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
