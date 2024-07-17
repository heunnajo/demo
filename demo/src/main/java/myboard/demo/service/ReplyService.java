package myboard.demo.service;

import lombok.RequiredArgsConstructor;
import myboard.demo.domain.Board;
import myboard.demo.domain.Reply;
import myboard.demo.domain.User;
import myboard.demo.dto.reply.addReplyRequest;
import myboard.demo.dto.reply.updateReplyRequest;
import myboard.demo.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final JpaReplyRepository jpaReplyRepository;
    private final JpaBoardRepository jpaBoardRepository;
    private final JpaUserRepository jpaUserRepository;

    //댓글 추가 메서드
    public Reply save(Long id,addReplyRequest request, String userId) {
        Optional<User> userOptional = jpaUserRepository.findByMemberId(userId);
        User user;
        if (userOptional.isPresent()) { // Optional이 값으로 채워져 있는지 확인
            user = userOptional.get(); // User 객체 추출
        } else {
            System.out.println("사용자가 존재하지 않습니다: " + userId);
            return null;
        }
        Board Board = jpaBoardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + id));

        request.setUser(user);
        request.setBoard(Board);

        return jpaReplyRepository.save(request.toEntity());
    }
    //댓글을 읽어온다.
    @Transactional(readOnly = true)
    public List<Reply> findAll(Long id) {
        Board Board = jpaBoardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));
        List<Reply> Replys = Board.getReplies();
        return Replys;
    }

    //댓글 업데이트
    @Transactional
    public void update(Long BoardId, Long id, updateReplyRequest dto) {
        Reply Reply = jpaReplyRepository.findByBoardIdAndId(BoardId, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));

        Reply.update(dto.getReply());
    }

    //댓글 삭제
    @Transactional
    public void delete(Long BoardId, Long id) {
        Reply Reply = jpaReplyRepository.findByBoardIdAndId(BoardId, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));

        jpaReplyRepository.delete(Reply);
    }
    //게시글을 작성한 유저인지 확인
    private static void authorizedBoardAuthor(Board Board){
        String UserName= SecurityContextHolder.getContext().getAuthentication().getName();
        if(!Board.getWriter().equals(UserName)){

        }
    }
}
