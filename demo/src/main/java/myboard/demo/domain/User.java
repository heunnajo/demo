package myboard.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter @Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;//시스템 내부적으로 사용되는 id
    @Column
    private String memberId;//사용자가 회원가입시에 정하는 id
    @Column
    private String pw;
    @Column
    private String name;
    @Column
    @CreatedDate
    private LocalDateTime regdate;
    @Column
    private String email;

    @Builder
    public User(String email,String pw, String memberId){
        this.email = email;
        this.pw = pw;
        this.memberId = memberId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    //추가 구현
    //계정 만료 여부
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    //계정 잠금 여부
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    //패스워드 만료 여부
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    //계정 사용 가능 여부
    public boolean isEnabled(){
        return true;
    }
    //사용자 아이디 변경 : 실제로 어떤 서비스 이용중에 아이디 변경되는 사례는 없기 때문에 이건 굳이 필요없을 것 같은데...
    /*public User update(String memberId){
        this.memberId = memberId;
        return this;
    }*/
}
