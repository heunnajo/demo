package myboard.demo.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import myboard.demo.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
//public class BoardRepository extends JpaRepository<Board,Long> {
public class BoardRepository {
    //스프링이 JPA의 EntityManager 생성해서 자동 주입
    private final EntityManager em;

    public Board save(Board board){
        //영속성 컨텍스트에 board 넣는다.
        //트랜잭션이 커밋되는 시점에 DB에 반영된다.(DB에 insert 쿼리가 날아간다.)
        em.persist(board);
        return board;
    }
//    Page<Board> findAll(Pageable pageable);

    public List<Board> findAll(){
        return em.createQuery("select b from Board b",Board.class)
                .getResultList();
    }

    public Board findById(Long id){
        //JPA의 find 메서드 사용
        return em.find(Board.class,id);
    }
    public List<Board> findByTitle(String title){
        return em.createQuery("select b from Board b where b.title = :title",Board.class)
                .setParameter("title",title)
                .getResultList();
    }

    public List<Board> findByContent(String content){
        return em.createQuery("select b from Board b where b.content = :content",Board.class)
                .setParameter("content",content)
                .getResultList();
    }

    public List<Board> findByWriter(String writer){
        return em.createQuery("select b from Board b where b.writer = :writer",Board.class)
                .setParameter("writer",writer)
                .getResultList();
    }

    public void deleteById(Long id){
        //soft delete : 데이터(테이블의 row)를 완전히 삭제하지 않고, 현재 타임스탬프로 저장
        em.createQuery("update Board b set deldate = CURRENT_TIMESTAMP where b.id = :id",Board.class);
    }
}
