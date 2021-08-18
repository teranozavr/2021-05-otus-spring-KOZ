package learning.spring.dao;

import learning.spring.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDaoJdbc implements AuthorDao{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void insert(Author author) {

    }

    @Override
    public Author getById(long id) {
        String SQL = "select * from author where id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        ((MapSqlParameterSource) namedParameters).addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new AuthorDaoJdbc.AuthorMapper());
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
