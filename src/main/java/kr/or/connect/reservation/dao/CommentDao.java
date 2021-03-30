package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.dto.ReservationUserCommentImage;
import kr.or.connect.reservation.infra.FileInfo;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CommentDao {
    private SimpleJdbcInsert insertComment;
    private SimpleJdbcInsert insertImages;


    public CommentDao(DataSource dataSource) {
        this.insertComment = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_user_comment")
                .usingGeneratedKeyColumns("id");
        this.insertImages =  new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_user_comment_image")
                .usingGeneratedKeyColumns("id");
    }

    public Long addComment(ReservationUserComment comment) {
        return insertComment.executeAndReturnKey(new BeanPropertySqlParameterSource(comment)).longValue();
    }

    public Long addImage(ReservationUserCommentImage image) {
        return insertImages.executeAndReturnKey(new BeanPropertySqlParameterSource(image)).longValue();
    }


}
