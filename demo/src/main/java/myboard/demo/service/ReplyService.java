package myboard.demo.service;

import lombok.RequiredArgsConstructor;
import myboard.demo.domain.Board;
import myboard.demo.domain.Reply;
import myboard.demo.repository.BoardRepository;
import myboard.demo.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    public Reply save(Reply reply){
        Reply savedReply = replyRepository.save(reply);
        return savedReply;
    }

    public List<Reply> findAll(Long id){
        //JpaRepository를 상속받지 않고 repository 구현했을 때, 어떻게?
//        Board board = boardRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));
        Board board = boardRepository.findById(id);
        List<Reply> replies = board.getReplies();
        return replies;
    }
    public List<Reply> findReplyByContent(String content){
        List<Reply> replyList = replyRepository.findByContent(content);
        return replyList;
    }

    public List<Board> findReplyByWriter(String writer){
        List<Board> boardList = replyRepository.findByWriter(writer);
        return boardList;
    }
    public void update(Long boardId, Long replyId, String content){
        Reply reply = replyRepository.findByBoardIdAndReplyId(boardId,replyId,content);
        reply.update(content);
    }
    public void deleteById(Long id){
        replyRepository.deleteById(id);
    }
}
