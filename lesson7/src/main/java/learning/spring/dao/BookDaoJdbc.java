package learning.spring.dao;

import learning.spring.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    @Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int count() {
        SqlParameterSource param = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject("select count(id) from book", param ,  Integer.class);
    }

    @Override
    public Book getById(long id) {
        String sql = "select * from book where id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        ((MapSqlParameterSource) namedParameters).addValue("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new BookMapper());
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Book getByParams(String title, Long authorId, Long genreId) {
        String sql = "select id, title, author_id, genre_id from book where (title = :title and author_id = :authorId and genre_id = :genreId)";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        ((MapSqlParameterSource) namedParameters).addValue("title", title);
        ((MapSqlParameterSource) namedParameters).addValue("authorId", authorId);
        ((MapSqlParameterSource) namedParameters).addValue("genreId", genreId);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new BookMapper());
    }

    @Override
    public List<Book> getByTitle(String title) {
        String sql = "select id, title, author_id, genre_id from book where title like :title";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        ((MapSqlParameterSource) namedParameters).addValue("title", "%"+title+"%");
            return namedParameterJdbcTemplate.query(sql, namedParameters, new BookMapper());
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
            String sql = "update book set title = :title where id = :bookId";
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("title", title);
            namedParameters.addValue("bookId", bookId);
            return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public int createBook(String title, Long authorId, Long genreId) {
        try {
            String sql = "insert into BOOK (ID, TITLE, AUTHOR_ID, GENRE_ID) values ((SELECT nextval('BOOK_ID')), :title, :authorId, :genreId);";
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("title", title);
            paramMap.put("authorId", authorId);
            paramMap.put("genreId", genreId);
            int createStatus = namedParameterJdbcTemplate.update(sql, paramMap);

            System.out.println("createStatus = "+ createStatus);
            return createStatus;
        }
        catch (Exception e){
            if(e instanceof DuplicateKeyException) {
                return -1;
            }
            System.out.println(e);
        }
        return -2;
    }

    @Override
    public int deleteBook(Long bookId) {
        String sql = "delete from BOOK where id = :id";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", bookId);
        return namedParameterJdbcTemplate.update(sql, paramMap);
    }
}
