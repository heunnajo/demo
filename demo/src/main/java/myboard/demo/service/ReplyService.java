package myboard.demo.service;

import lombok.RequiredArgsConstructor;
import myboard.demo.domain.Board;
import myboard.demo.domain.Reply;
import myboard.demo.domain.User;
import myboard.demo.dto.reply.addReplyRequest;
import myboard.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final JpaReplyRepository jpaReplyRepository;
    private final JpaBoardRepository jpaBoardRepository;
    private final JpaUserRepository jpaUserRepository;

    public Reply save(Long id, addReplyRequest request, String userId){
        Optional<User> userOptional = jpaUserRepository.findById(userId);
        User user;
        if(userOptional.isPresent()){
            user = userOptional.get();
        } else{
            System.out.println("사용자가 존재하지 않습니다: "+ userId);
        }
        Board board = jpaBoardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패 : 해당 게시글이 존재하지 않습니다."+id));

        request.setUser(user);
        request.setBoard(board);

        return jpaReplyRepository.save(request.toEntity());
    }
}
