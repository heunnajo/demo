package myboard.demo.controller;

import lombok.RequiredArgsConstructor;
import myboard.demo.domain.Board;
import myboard.demo.domain.Reply;
import myboard.demo.dto.reply.addReplyRequest;
import myboard.demo.dto.reply.updateReplyRequest;
import myboard.demo.service.BoardService;
import myboard.demo.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReplyController {
    private final ReplyService replyService;

    //댓글 생성
    @PostMapping("/boards/{id}/replies")
    public ResponseEntity<Reply> save(@PathVariable Long replyId, @RequestBody addReplyRequest request, Principal principal){
        Reply savedReply = replyService.save(replyId,request,principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReply);
    }

    //댓글 읽어오기
    @GetMapping("/boards/{id}/replies")
    public List<Reply> read(@PathVariable long id){
        return replyService.findAll(id);
    }

    //댓글 업데이트
    @PutMapping("/boards/{boardId}/replies/{id}")
    public ResponseEntity<Long> update(@PathVariable long boardId, @PathVariable Long id, @RequestBody updateReplyRequest dto){
        replyService.update(boardId,id,dto);
        return ResponseEntity.ok(id);
    }
    //댓글 삭제
    @DeleteMapping("/boards/{boardId}/replies/{id}")
    public ResponseEntity<Long> delete(@PathVariable long boardId, @PathVariable Long id){
        replyService.delete(boardId,id);
        return ResponseEntity.ok(id);
    }
}
