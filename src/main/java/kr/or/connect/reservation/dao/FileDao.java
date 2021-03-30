package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.infra.FileInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class FileDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<FileInfo> rowMapper = BeanPropertyRowMapper.newInstance(FileInfo.class);
    private SimpleJdbcInsert insertFile;

    public FileDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertFile = new SimpleJdbcInsert(dataSource)
                .withTableName("file_info")
                .usingGeneratedKeyColumns("id");
    }

    public Long addFile(FileInfo fileInfo) {
        return insertFile.executeAndReturnKey(new BeanPropertySqlParameterSource(fileInfo)).longValue();
    }

    public FileInfo getFile(Long fileId) {
        Map<String, Long> param = new HashMap<>();
        param.put("fileId", fileId);
        return jdbc.queryForObject("select * from file_info where id = :fileId", param, rowMapper);
    }
}
