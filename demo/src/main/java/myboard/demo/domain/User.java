package myboard.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    private String memberId;
    private String pw;
    private String name;
    private LocalDateTime regdate;
    private String email;

}
