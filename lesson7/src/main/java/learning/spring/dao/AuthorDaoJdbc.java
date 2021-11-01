package learning.spring.dao;

import learning.spring.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Author getById(Long id) {
        String sql = "select id, first_name, surname, middle_name from author where id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new AuthorDaoJdbc.AuthorMapper());
    }

    @Override
    public int createAuthor(Author author) {
        String sql = "insert into author (first_name, surname, middle_name) values (:name, :surname, :middlename);";
        return namedParameterJdbcTemplate.update(sql, Map.of("name", author.getFirstName(),
                "surname", author.getSurName(),
                "middlename", author.getMiddleName()));
    }

    @Override
    public Author getByName(Author author) {
        String sql = "select id, first_name, surname, middle_name from author where first_name = :name and surname = :surname and middle_name = :middlename;";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("name", author.getFirstName(),
                "surname", author.getSurName(),
                "middlename", author.getMiddleName()),
                new AuthorDaoJdbc.AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String firstName = resultSet.getString("first_name");
            String surName = resultSet.getString("surname");
            String middleName = resultSet.getString("middle_name");
            return new Author(id, firstName, surName, middleName);
        }
    }
}
