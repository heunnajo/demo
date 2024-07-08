package myboard.demo.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myboard.demo.domain.Board;

@NoArgsConstructor//기본 생성자 추가
@AllArgsConstructor//모든 필드 값을 파라미터로 받음
@Getter//모든 필드에 접근자 메서드
public class addBoardRequest {
    private String title;
    private String content;

    public Board toEntity(String writer){
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
