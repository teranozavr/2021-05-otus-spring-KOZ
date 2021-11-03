package learning.spring.dao;

import learning.spring.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    GenreDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int createGenre(Genre genre) {
        String sql = "insert into GENRE (GENRE_NAME) values (:name);";
        return namedParameterJdbcTemplate.update(sql, Map.of("name", genre.getGenreName()));
    }

    @Override
    public Genre getById(long id) {
        String sql = "select id, genre_name from genre where id = :id";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("id", id), new GenreDaoJdbc.GenreMapper());
    }

    @Override
    public Genre getByName(Genre genre) {
        String sql = "select id, genre_name from genre where genre_name = :name";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("name", genre.getGenreName(),
                "id", genre.getId()), new GenreDaoJdbc.GenreMapper());
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
