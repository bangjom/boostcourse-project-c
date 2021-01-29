package kr.or.connect.reservation.service.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRoleEntity {
    private String userLoginId;
    private String roleName;
}
