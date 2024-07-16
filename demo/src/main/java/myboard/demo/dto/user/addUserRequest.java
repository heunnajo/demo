package myboard.demo.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class addUserRequest {
    private String email;
    private String password;
}
