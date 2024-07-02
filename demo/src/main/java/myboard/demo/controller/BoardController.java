package myboard.demo.controller;

import lombok.RequiredArgsConstructor;
import myboard.demo.domain.Board;
import myboard.demo.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<?> postBoard(@RequestBody Board board){
        Board savedBoard = boardService.save(board);
        return new ResponseEntity<>(savedBoard, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> getBoards(){
        List<Board> list = boardService.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> putBoard(@PathVariable("id") Long id, @RequestBody Board board){
        Board persistBoard = boardService.findBoardById(id);
        persistBoard.update(board);
        Board savedBoard = boardService.save(persistBoard);
        return new ResponseEntity<>(savedBoard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id){
        boardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
