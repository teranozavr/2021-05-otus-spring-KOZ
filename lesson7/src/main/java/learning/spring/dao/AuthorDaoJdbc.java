package learning.spring.dao;

import learning.spring.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Author getById(long id) {
        String SQL = "select * from author where id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        ((MapSqlParameterSource) namedParameters).addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new AuthorDaoJdbc.AuthorMapper());
    }

    @Override
    public int createAuthor(String name, String surname, String middlename) {
        String SQL = "insert into AUTHOR (ID, FIRST_NAME, SURNAME, MIDDLE_NAME) values ((SELECT nextval('author_id')), :name, :surname, :middlename);";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", name);
        paramMap.put("surname", surname);
        paramMap.put("middlename", middlename);
        return namedParameterJdbcTemplate.update(SQL, paramMap);
    }

    @Override
    public Author getByName(String name, String surname, String middlename) {
        String SQL = "select * from author where first_name = :name and surname = :surname and middle_name = :middlename;";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        ((MapSqlParameterSource) namedParameters).addValue("name", name);
        ((MapSqlParameterSource) namedParameters).addValue("surname", surname);
        ((MapSqlParameterSource) namedParameters).addValue("middlename", middlename);
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
