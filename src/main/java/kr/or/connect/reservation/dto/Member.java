package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
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
    }

    public Member(String name, String password, String email, LocalDateTime createDate, LocalDateTime modifyDate) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }
}
