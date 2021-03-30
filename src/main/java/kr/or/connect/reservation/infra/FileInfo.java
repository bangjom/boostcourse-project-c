package kr.or.connect.reservation.infra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfo {
    private Long id;
    private String fileName;
    private String saveFileName;
    private String contentType;
    private Integer deleteFlag;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
