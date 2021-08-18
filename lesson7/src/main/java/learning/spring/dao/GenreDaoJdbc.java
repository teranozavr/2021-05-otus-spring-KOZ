package learning.spring.dao;

import learning.spring.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreDaoJdbc implements GenreDao{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Genre getByName(String name) {
        return null;
    }

    @Override
    public Genre getById(long id) {
        String SQL = "select * from genre where id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        ((MapSqlParameterSource) namedParameters).addValue("id", id);
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
