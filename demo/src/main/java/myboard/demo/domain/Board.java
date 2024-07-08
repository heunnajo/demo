package myboard.demo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Board {
    @Id
    @GeneratedValue
    @Column(name="board_id")
    private Long id;

    @ManyToOne
    private String writer;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private LocalDateTime regdate;
    @Column
    private LocalDateTime deldate;
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Reply> replies;

    @Builder
    public Board(Long id,String writer,String title,String content,LocalDateTime regdate){
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.regdate = regdate;
    }

    public void update(Board board){
        this.title = board.getTitle();
        this.content = board.getContent();
    }

    @LastModifiedDate//엔티티가 수정될 때 수정 시간 저장
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Reply> replies;
}
