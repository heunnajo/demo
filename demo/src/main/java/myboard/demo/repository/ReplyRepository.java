package myboard.demo.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import myboard.demo.domain.Board;
import myboard.demo.domain.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
//public class BoardRepository extends JpaRepository<Board,Long> {
public class ReplyRepository {
    //스프링이 JPA의 EntityManager 생성해서 자동 주입
    private final EntityManager em;

    public Reply save(Reply reply){
        //영속성 컨텍스트에 board 넣는다.
        //트랜잭션이 커밋되는 시점에 DB에 반영된다.(DB에 insert 쿼리가 날아간다.)
        em.persist(reply);
        return reply;
    }
//    Page<Board> findAll(Pageable pageable);
    public List<Reply> findByBoardId(Long boardId, String content){
        return em.createQuery("select r from Reply r where r.content = :content",Reply.class)
                .setParameter("content",content)
                .getResultList();
    }
    public Reply findByBoardIdAndReplyId(Long boardId, Long replyId, String content){
        return em.createQuery("select r from Reply r where r.board_id = ? and r.reply_id = ?",Reply.class)
                .setParameter(1, boardId.intValue())
                .setParameter(2, replyId.intValue())
                .getSingleResult();
    }
    public List<Reply> findByContent(String content){
        return em.createQuery("select r from Reply r where r.content = :content",Reply.class)
                .setParameter("content",content)
                .getResultList();
    }

    public List<Board> findByWriter(String writer){
        return em.createQuery("select r from Board r where r.writer = :writer",Board.class)
                .setParameter("writer",writer)
                .getResultList();
    }

    public void deleteById(Long id){
        //soft delete : 데이터(테이블의 row)를 완전히 삭제하지 않고, 현재 타임스탬프로 저장
        em.createQuery("update Reply r set deldate = CURRENT_TIMESTAMP where r.id = :id",Board.class);
    }
}
