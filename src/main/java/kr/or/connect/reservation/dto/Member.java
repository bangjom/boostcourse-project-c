package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private Long id;
    private String name;
    private String password;
    private String tel;
    private String email;
    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime modifyDate = LocalDateTime.now();

    public Member(String name, String password, String tel, String email) {
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.email = email;
    }
}
