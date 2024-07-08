package myboard.demo.controller;

import lombok.RequiredArgsConstructor;
import myboard.demo.domain.Board;
import myboard.demo.dto.board.BoardResponse;
import myboard.demo.dto.board.addBoardRequest;
import myboard.demo.dto.board.updateBoardRequest;
import myboard.demo.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 작성
    //principal : 현재 인증 정보를 가져온다.
    @PostMapping("/api/boards")
    public ResponseEntity<Board> addBoard(@RequestBody addBoardRequest request, Principal principal){
        Board savedBoard = boardService.save(request,principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBoard);
    }
    // 게시글 전체 목록 조회
    @GetMapping("/api/boards")
    public ResponseEntity<List<BoardResponse>> findAllBoards(){
        List<BoardResponse> boards = boardService.findAll()
                .stream()
                .map(BoardResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(boards);
    }
    // 게시글 단건 조회
    @GetMapping("/api/boards/{id}")
    public ResponseEntity<BoardResponse> findBoard(@PathVariable long id){
        Board board = boardService.findById(id);

        return ResponseEntity.ok()//글을 찾으면
                .body(new BoardResponse(board));
    }
    // 게시글 단건 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long id){
        boardService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
    //게시글 단건 수정
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable long id,
                                             @RequestBody updateBoardRequest request){
        Board updatedBoard = boardService.update(id,request);
        return ResponseEntity.ok()
                .body(updatedBoard);
    }
}
