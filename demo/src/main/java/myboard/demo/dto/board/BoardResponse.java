package myboard.demo.dto.board;

import lombok.Getter;
import myboard.demo.domain.Board;

@Getter
public class BoardResponse {
    private final String title;
    private final String content;

    public BoardResponse(Board board){
        this.title = board.getTitle();
        this.content = board.getTitle();
    }
}
