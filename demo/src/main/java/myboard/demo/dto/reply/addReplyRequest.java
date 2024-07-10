package myboard.demo.dto.reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myboard.demo.domain.Board;
import myboard.demo.domain.Reply;
import myboard.demo.domain.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class addReplyRequest {
    private Long id;
    private String content;
    private LocalDateTime regdate = LocalDateTime.now();
    private User user;
    private Board board;

    public Reply toEntity(){//생성자를 사용해 객체 생성
        return Reply.builder()
                .content(content)
                .regdate(regdate)
                .board(board)
                .user(user)
                .build();

    }

}
