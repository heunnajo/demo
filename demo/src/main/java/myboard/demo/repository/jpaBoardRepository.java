package myboard.demo.repository;

import myboard.demo.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface jpaBoardRepository extends JpaRepository<Board,Long> {

}
