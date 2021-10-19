package learning.spring.dao;

import learning.spring.domain.Genre;
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
public class GenreDaoJdbc implements GenreDao{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int createGenre(Genre genre) {
        String sql = "insert into GENRE (ID, GENRE_NAME) values ((SELECT nextval('GENRE_ID')), :name);";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", genre.getGenreName());
        return namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public Genre getById(long id) {
        String sql = "select id, genre_name from genre where id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new GenreDaoJdbc.GenreMapper());
    }

    @Override
    public Genre getByName(Genre genre) {
        String sql = "select id, genre_name from genre where genre_name = :name";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("name", genre.getGenreName());
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new GenreDaoJdbc.GenreMapper());
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
