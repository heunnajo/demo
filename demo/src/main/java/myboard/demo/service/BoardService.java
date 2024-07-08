package myboard.demo.service;

import lombok.RequiredArgsConstructor;
import myboard.demo.domain.Board;
import myboard.demo.repository.BoardRepository;
import myboard.demo.repository.jpaBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final jpaBoardRepository boardRepository;

    public Board save(Board board){
        Board savedBoard = boardRepository.save(board);
        return savedBoard;
    }

//    public Page<Board> findAll(Pageable pageable){
//        return boardRepository.findAll(pageable);
//    }
    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    public Board findBoardById(Long id){
        Board board = boardRepository.findById(id);//
        return board;
    }
    public List<Board> findBoardByTitle(String title){
        List<Board> boardList = boardRepository.findByTitle(title);
        return boardList;
    }

    public List<Board> findBoardByContent(String content){
        List<Board> boardList = boardRepository.findByTitle(content);
        return boardList;
    }

    public List<Board> findBoardByWriter(String writer){
        List<Board> boardList = boardRepository.findByTitle(writer);
        return boardList;
    }

    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }

    //게시글을 작성한 유저인지 확인
    private static void authorizeArticleAuthor(Board article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!article.getWriter().equals(userName)){
            throw new IllegalArgumentException("not authorized");
        }
    }
    public Board saveComment(AddArticleRequest request,String userName){//AddArticleRequest 클래스에 저장된 값들을 article 데이터베이스에 저장한다.
        return jpaBoardRepository.save(request.toEntity(userName));
    }
}
