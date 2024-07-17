package myboard.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    @GetMapping("/login")
    public String login(){
        return "oauthLogin";
    }
    @GetMapping
    public String signup(){
        return "signup";
    }
}
