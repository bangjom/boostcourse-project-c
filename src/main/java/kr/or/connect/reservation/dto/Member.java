package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Long id;
    private String name;
    private String password;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public Member() {
        createDate = LocalDateTime.now();
        modifyDate = LocalDateTime.now();
    }

    public Member(Long id, String name, String password, String email) {
        this();
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
