package learning.spring.dao;

import learning.spring.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GenreDaoJdbc implements GenreDao{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int createGenre(String name) {
        String SQL = "insert into GENRE (ID, GENRE_NAME) values ((SELECT nextval('GENRE_ID')), :name);";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", name);
        return namedParameterJdbcTemplate.update(SQL, paramMap);
    }

    @Override
    public Genre getById(long id) {
        String SQL = "select * from genre where id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        ((MapSqlParameterSource) namedParameters).addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new GenreDaoJdbc.GenreMapper());
    }

    @Override
    public Genre getByName(String name) {
        String SQL = "select * from genre where genre_name = :name";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        ((MapSqlParameterSource) namedParameters).addValue("name", name);
        return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new GenreDaoJdbc.GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String genreName = resultSet.getString("genre_name");
            return new Genre(id, genreName);
        }
    }
}
