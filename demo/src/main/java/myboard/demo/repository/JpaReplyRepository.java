package myboard.demo.repository;

import myboard.demo.domain.Board;
import myboard.demo.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaReplyRepository extends JpaRepository<Reply,Long> {
    List<Reply> getReplyByBoardOrderById(Board board);
    Optional<Reply> findByBoardAndId(Long boardId, Long id);
}

