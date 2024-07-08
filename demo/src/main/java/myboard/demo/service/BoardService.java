package myboard.demo.service;

import lombok.RequiredArgsConstructor;
import myboard.demo.domain.Board;
import myboard.demo.dto.board.addBoardRequest;
import myboard.demo.dto.board.updateBoardRequest;
import myboard.demo.repository.jpaBoardRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final jpaBoardRepository jpaBoardRepository;
    private final myboard.demo.repository.jpaUserRepository jpaUserRepository;
    public Board save(addBoardRequest request, String userId){
        return jpaBoardRepository.save(request.toEntity(userId));
    }
    public List<Board> findAll(){
        return jpaBoardRepository.findAll();
    }
    public Board findById(long id){
        return jpaBoardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: "+id));
    }
    //수정, 삭제는 해당 게시글을 작성한 사용자만 가능
    public void delete(long id){
        Board board = jpaBoardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: "+id));
        authorizerArticleAuthor(board);
        jpaBoardRepository.delete(board);
    }
    public Board update(long id, updateBoardRequest request){
        Board board = jpaBoardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: "+id));
        authorizerArticleAuthor(board);
        board.update(board);
        return board;
    }

    //게시글을 작성한 유저인지 확인
    private static void authorizerArticleAuthor(Board board){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!board.getWriter().equals(userName)){
            throw new IllegalArgumentException("not authorized");
        }
    }
    public Board saveComment(addBoardRequest request, String userName){
        return jpaBoardRepository.save(request.toEntity(userName));
    }

}
