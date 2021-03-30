package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dao.FileDao;
import kr.or.connect.reservation.dao.ReservationDao;
import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.infra.FileInfo;
import kr.or.connect.reservation.infra.Uploader;
import kr.or.connect.reservation.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final UserDao userDao;
    private final ReservationDao reservationDao;
    private final CommentDao commentDao;
    private final FileDao fileDao;
    private final Uploader uploader;

    public PostCommentsResponse postComments(MultipartFile multipartFile, CommentVo request, Principal principal) {
        User findUser = userDao.getMemberByEmail(principal.getName());
        Reservation findReservation = reservationDao.findReservationById(request.getReservationInfoId().longValue());
        ReservationUserComment comment = request.toComment(findReservation.getProductId(), findUser.getId());
        Long commentId = commentDao.addComment(comment);
        FileInfo upload = uploader.upload(multipartFile);
        Long fileId = fileDao.addFile(upload);
        ReservationUserCommentImage image = ReservationUserCommentImage.builder()
                .reservationInfoId(findReservation.getId())
                .reservationUserCommentId(commentId)
                .fileId(fileId)
                .build();
        Long imageId = commentDao.addImage(image);
        PostCommentsResponse result = new PostCommentsResponse("fail", findReservation.getProductId().intValue());
        if (commentId != null && fileId != null && imageId != null) {
            result.updateSuccess();
        }
        return result;
    }

    public FileInfo getImageFile(Long fileId) {
        return fileDao.getFile(fileId);
    }
}
