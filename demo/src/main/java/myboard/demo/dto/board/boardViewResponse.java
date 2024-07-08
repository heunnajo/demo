package myboard.demo.dto.board;

import lombok.Getter;
import myboard.demo.domain.Board;

@Getter
public class boardViewResponse {
    private final Long id;
    private final String title;
    private final String content;

    private final String author;
    public ArticleListViewResponse(Board board){
        this.id =board.getId();
        this.title= board.getTitle();
        this.content = board.getContent();
        this.author =board.getWriter();
    }
}
