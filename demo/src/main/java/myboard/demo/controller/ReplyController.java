package myboard.demo.controller;

import lombok.RequiredArgsConstructor;
import myboard.demo.domain.Board;
import myboard.demo.domain.Reply;
import myboard.demo.service.BoardService;
import myboard.demo.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/{boardId}/replies")
    public ResponseEntity<?> save(@PathVariable Long id, @RequestBody Reply reply, Principal principal){
        Reply savedReply = replyService.save(reply);
        return new ResponseEntity<>(savedReply, HttpStatus.CREATED);
    }
    @GetMapping("/{boardId}/replies")
    public ResponseEntity<?> getReplies(@PathVariable Long id){
        List<Reply> list = replyService.findAll(id);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{boardId}/replies/{replyId}")
    public ResponseEntity<?> update(@PathVariable Long boardId, @PathVariable Long replyId, @RequestBody Reply updatedComment){
        replyService.update(boardId, replyId, updatedComment);
        return ResponseEntity.ok(replyId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id){
        boardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
