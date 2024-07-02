package myboard.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Reply {
    @Id
    @GeneratedValue
    @Column(name="reply_id")
    private Long replyId;

//    @Column
//    private Long boardId;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
//    @Column
//    private String writer;
    @ManyToOne
    @JoinColumn(name = "writer")
    private User user;
    @Column
    private String content;
    @Column
    private LocalDateTime regdate;
    @Column
    private LocalDateTime deldate;

    @Builder
    public Reply(Long id,User writer,String title,String content,LocalDateTime regdate){
        this.user = writer;
        this.content = content;
        this.regdate = regdate;
    }

    public void update(Reply reply){
        this.content = reply.getContent();
    }
    public void update(String content){
        this.content = content;
    }
}
