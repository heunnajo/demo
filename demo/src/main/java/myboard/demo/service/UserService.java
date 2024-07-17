package myboard.demo.service;

import lombok.RequiredArgsConstructor;
import myboard.demo.domain.User;
import myboard.demo.dto.user.addUserRequest;
import myboard.demo.repository.JpaUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final JpaUserRepository jpaUserRepository;

    public Long save(addUserRequest dto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return jpaUserRepository.save(User.builder())
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .memberId(dto.getEmail())
                .build().getId();

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
