package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRole {
    private Long id;
    private Long memberId;
    private String roleName;

    public MemberRole() {
    }

    public MemberRole(Long memberId, String roleName) {
        this.memberId = memberId;
        this.roleName = roleName;
    }
}
