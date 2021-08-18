package learning.spring.dao;

import learning.spring.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDaoJdbc implements BookDao {

    @Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int count() {
        SqlParameterSource param = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject("select count(*) from book", param ,  Integer.class);
    }

    @Override
    public void insert(Book book) {

    }

    @Override
    public Book getById(long id) {
        String SQL = "select * from book where id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        ((MapSqlParameterSource) namedParameters).addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public void deleteById(long id) {

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
}
