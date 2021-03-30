package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRole {
    private Long id;
    private Long userId;
    private String roleName;

    public UserRole() {
    }

    public UserRole(Long userId, String roleName) {
        this.userId = userId;
        this.roleName = roleName;
    }
}
