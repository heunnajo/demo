package myboard.demo.service;

import lombok.RequiredArgsConstructor;
import myboard.demo.domain.User;
import myboard.demo.repository.JpaUserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final JpaUserRepository jpaUserRepository;
    @Override
    public User loadUserByUsername(String email){
        return jpaUserRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException(email));
    }
}
