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
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    BookDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int count() {
        SqlParameterSource param = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject("select count(id) from book", param ,  Integer.class);
    }

    @Override
    public Book getById(long id) {
        String sql = "select b.id, b.title, b.author_id, b.genre_id, " +
                "a.id, a.first_name , a.middle_name, a.surname, " +
                "g.id, g.genre_name from book b " +
                "left join author a on b.author_id = a.id " +
                "left join genre g on b.genre_id = g.id " +
                "where  b.id = :id";
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, Map.of("id", id), new BookMapper());
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Book getByParams(Book book) {
        String sql = "select b.id, b.title, b.author_id, b.genre_id, " +
                "a.id, a.first_name , a.middle_name, a.surname, " +
                "g.id, g.genre_name from book b " +
                "left join author a on b.author_id = a.id " +
                "left join genre g on b.genre_id = g.id " +
                "where(title = :title and author_id = :authorId and genre_id = :genreId)";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("title", book.getTitle(),
                "authorId", getAuthorId(book),
                "genreId", getGenreId(book)), new BookMapper());
    }

    @Override
    public List<Book> getByTitle(String title) {
        String sql = "select b.id, b.title, b.author_id, b.genre_id, " +
                "a.id, a.first_name , a.middle_name, a.surname, " +
                "g.id, g.genre_name from book b " +
                "left join author a on b.author_id = a.id " +
                "left join genre g on b.genre_id = g.id " +
                "where title like :title";
            return namedParameterJdbcTemplate.query(sql, Map.of("title", "%"+title+"%"), new BookMapper());
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
            return namedParameterJdbcTemplate.update(sql, Map.of("title", title,
                    "bookId", bookId));
    }

    @Override
    public Long createBook(Book book) {
        try {
            String sql = "insert into BOOK (TITLE, AUTHOR_ID, GENRE_ID) values (:title, :authorId, :genreId);";
            namedParameterJdbcTemplate.update(sql, Map.of("title", book.getTitle(),
                    "authorId", getAuthorId(book),
                    "genreId", getGenreId(book)));
            return getByParams(book).getId();
        }
        catch (Exception e){
            if(e instanceof DuplicateKeyException) {
                log.error("Книга с данными параметрами уже сущестует!");
            }
            log.error("При создании книги произошла ошибка! {}", e.getMessage());
        }
        return null;
    }

    @Override
    public int deleteBook(Book book) {
        String sql = "delete from BOOK where id = :id";
        return namedParameterJdbcTemplate.update(sql, Map.of("id", book.getId()));
    }

    private long getAuthorId(Book book){
        return book.getAuthor().getId();
    }

    private long getGenreId(Book book){
        return book.getGenre().getId();
    }
}
