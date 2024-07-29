package myboard.demo;

import myboard.demo.domain.User;
import myboard.demo.repository.JpaUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Test
public class ApplicationTest {
    @Autowired
    JpaUserRepository jpaUserRepository;

    //1.
//    User user1 = User.builder()
//            .email("user1")
//            .pw("pwdpwd")
//            .memberId("goodday")
//            .build();
//    User user2 = User.builder()
//            .email("user2")
//            .pw("pwdpwd")
//            .memberId("goooodday")
//            .build();
//
//    List<User> userList = jpaUserRepository.findAll();
//    for(User user : userList){
//        System.out.println("user = " + user.getMemberId());
//    }

    //2,
    public void printAllUsers() {
        List<User> userList = jpaUserRepository.findAll();
        for(User user : userList){
            System.out.println("user = " + user.getMemberId());
        }
    }
}
