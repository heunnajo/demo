package myboard.demo.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import myboard.demo.domain.Board;

@Getter
@NoArgsConstructor
public class boardViewResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;

    public boardViewResponse(Board board){
        this.id =board.getId();
        this.title= board.getTitle();
        this.content = board.getContent();
        this.writer =board.getWriter();
    }
}
