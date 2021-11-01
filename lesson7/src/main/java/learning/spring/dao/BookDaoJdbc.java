package learning.spring.dao;

import learning.spring.domain.Author;
import learning.spring.domain.Book;
import learning.spring.domain.Genre;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
        String sql = "select b.id, b.title, b.author_id, b.genre_id, a.id, a.first_name , a.middle_name, a.surname, g.id, g.genre_name from book b join author a on b.author_id = a.id join genre g on b.genre_id = g.id where  b.id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new BookMapper());
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Book getByParams(Book book) {
        String sql = "select b.id, b.title, b.author_id, b.genre_id, a.id, a.first_name , a.middle_name, a.surname, g.id, g.genre_name from book b join author a on b.author_id = a.id join genre g on b.genre_id = g.id where(title = :title and author_id = :authorId and genre_id = :genreId)";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("title", book.getTitle());
        namedParameters.addValue("authorId", getAuthorId(book));
        namedParameters.addValue("genreId", getGenreId(book));
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new BookMapper());
    }

    @Override
    public List<Book> getByTitle(String title) {
        String sql = "select b.id, b.title, b.author_id, b.genre_id, a.id, a.first_name , a.middle_name, a.surname, g.id, g.genre_name from book b join author a on b.author_id = a.id join genre g on b.genre_id = g.id where title like :title";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("title", "%"+title+"%");
            return namedParameterJdbcTemplate.query(sql, namedParameters, new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            long authorId = resultSet.getLong("author_id");
            long genreId = resultSet.getLong("genre_id");
            String title = resultSet.getString("title");
            String firstName = resultSet.getString("first_name");
            String surName = resultSet.getString("surname");
            String middleName = resultSet.getString("middle_name");
            String genreName = resultSet.getString("genre_name");
            return new Book(id, new Author(authorId, firstName, surName, middleName), new Genre(genreId, genreName), title);
        }
    }
    @Override
    public int updateTitleById(Long bookId, String title){
            String sql = "update book set title = :title where id = :bookId";
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("title", title);
            namedParameters.addValue("bookId", bookId);
            return namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public int createBook(Book book) {
        try {
            String sql = "insert into BOOK (TITLE, AUTHOR_ID, GENRE_ID) values (:title, :authorId, :genreId);";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("title", book.getTitle());
            paramMap.put("authorId", getAuthorId(book));
            paramMap.put("genreId", getGenreId(book));
            int createStatus = namedParameterJdbcTemplate.update(sql, paramMap);
            return createStatus;
        }
        catch (Exception e){
            if(e instanceof DuplicateKeyException) {
                return -1;
            }
            log.error(e.getMessage());
        }
        return -2;
    }

    @Override
    public int deleteBook(Book book) {
        String sql = "delete from BOOK where id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", book.getId());
        return namedParameterJdbcTemplate.update(sql, paramMap);
    }

    private long getAuthorId(Book book){
        return book.getAuthor().getId();
    }

    private long getGenreId(Book book){
        return book.getGenre().getId();
    }
}
