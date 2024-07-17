package myboard.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import myboard.demo.dto.user.addUserRequest;
import myboard.demo.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController{
    private final UserService userService;
    @PostMapping("/user")
    public String signup(addUserRequest request){
        userService.save(request);//회원가입 메서드
        return "redirect:/login";//회원 가입이 완료된 이후에 로그인 페이지로 이동
    }
    @GetMapping("signup")
    public String signup(){
        return "signup";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
