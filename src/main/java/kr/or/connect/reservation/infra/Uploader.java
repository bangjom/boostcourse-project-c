package kr.or.connect.reservation.infra;


import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

@Component
public class Uploader {
    private final static String UPLOAD_PATH = "/tmp/";

    public FileInfo upload(MultipartFile file)  {
        File target = new File(UPLOAD_PATH , file.getOriginalFilename());
        if ( ! new File(UPLOAD_PATH).exists()) {
            new File(UPLOAD_PATH).mkdirs();
        }
        try {
            FileCopyUtils.copy(file.getBytes(), target);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return FileInfo.builder()
                .fileName(file.getOriginalFilename())
                .saveFileName(UPLOAD_PATH+file.getOriginalFilename())
                .contentType(file.getContentType())
                .deleteFlag(0)
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .build();
    }
}
