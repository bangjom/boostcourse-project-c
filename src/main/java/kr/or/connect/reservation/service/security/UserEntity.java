package kr.or.connect.reservation.service.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserEntity {
    private String loginUserId;
    private String password;
}
