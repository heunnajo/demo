package myboard.demo.service;

import lombok.RequiredArgsConstructor;
import myboard.demo.domain.User;
import myboard.demo.dto.user.addUserRequest;
import myboard.demo.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final JpaUserRepository jpaUserRepository;

    public Long save(addUserRequest dto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User savedUser = jpaUserRepository.save(User.builder()
                        .email(dto.getEmail())
                        .pw(encoder.encode(dto.getPassword()))
                        .memberId(dto.getMemberId())
                        .build());
        return savedUser.getId();
    }

    public User findById(String memberId){
        return jpaUserRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email){
        return jpaUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
