package learning.spring.dao;

import learning.spring.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDaoJdbc implements BookDao {

    @Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int count() {
        SqlParameterSource param = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject("select count(*) from book", param ,  Integer.class);
    }

    @Override
    public Book getById(long id) {
        String SQL = "select * from book where id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        ((MapSqlParameterSource) namedParameters).addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new BookMapper());
    }

    @Override
    public List<Book> getByTitle(String title) {
        String SQL = "select * from book where title like :title";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        ((MapSqlParameterSource) namedParameters).addValue("title", "%"+title+"%");
        return namedParameterJdbcTemplate.query(SQL, namedParameters, new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            long authorId = resultSet.getLong("author_id");
            long genreId = resultSet.getLong("genre_id");
            String title = resultSet.getString("title");
            return new Book(id, authorId, genreId, title);
        }
    }
    @Override
    public int setTitle(Long bookId, String title){
            String SQL = "update book set title = :title where id = :bookId";
            SqlParameterSource namedParameters = new MapSqlParameterSource();
            ((MapSqlParameterSource) namedParameters).addValue("title", title);
            ((MapSqlParameterSource) namedParameters).addValue("bookId", bookId);
            return namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    @Override
    public int createBook(String title, Long authorId, Long genreId) {
        try {
            String SQL = "insert into BOOK (ID, TITLE, AUTHOR_ID, GENRE_ID) values ((SELECT nextval('AUTHOR_ID')), :title, :authorId, :genreId);";
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("title", title);
            paramMap.put("authorId", authorId);
            paramMap.put("genreId", genreId);
            return namedParameterJdbcTemplate.update(SQL, paramMap);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return -1;
    }

    @Override
    public int deleteBook(Long bookId) {
        String SQL = "delete from BOOK where id = :id";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", bookId);
        return namedParameterJdbcTemplate.update(SQL, paramMap);
    }
}
