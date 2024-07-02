package myboard.demo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
