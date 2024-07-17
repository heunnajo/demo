package myboard.demo.repository;
import myboard.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface JpaUserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByMemberId(String memberId);
}