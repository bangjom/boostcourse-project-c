package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.infra.FileInfo;
import kr.or.connect.reservation.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("api/files")
@RequiredArgsConstructor
public class FileController {
    private final CommentService commentService;

    @GetMapping("/{fileId}")
    public void downloadFile(@PathVariable Long fileId, HttpServletResponse response) throws IOException {
        FileInfo fileInfo = commentService.getImageFile(fileId);

        String saveFileName = fileInfo.getSaveFileName();
        String contentType = fileInfo.getContentType();

        File file = new File(saveFileName);

        response.setContentType(contentType);
        response.setContentLength((int) file.length());
        String fileName = new String(file.getName().getBytes("utf-8"));
        response.setHeader("Content-Disposition", "attachment; filename=\" " + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        try (
                OutputStream out = response.getOutputStream();
                FileInputStream fis = new FileInputStream(file)
        ) {
            FileCopyUtils.copy(fis, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
