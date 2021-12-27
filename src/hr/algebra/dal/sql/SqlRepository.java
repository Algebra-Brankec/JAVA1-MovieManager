/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.sql;

import com.sun.corba.se.impl.interceptors.PICurrent;
import hr.algebra.dal.Repository;
import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author dnlbe
 */
public class SqlRepository implements Repository {

    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String PUBLISHED_DATE = "PublishedDate";    
    private static final String DESCRIPTION = "Description";
    private static final String ORIG_NAME = "OrigName";
    private static final String TIME = "Time";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String LINK = "Link";
    private static final String GUID = "GUID";
    private static final String START_DATE = "StartDate";
    
    private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?,?,?,?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL selectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL selectMovies }";
    
    private static final String ID_ACTOR = "IDActor";
    private static final String ID_DIRECTOR = "IDDirector";
    private static final String ID_GENRE = "IDGenre";
    
    private static final String NAME = "Name";
    private static final String MOVIE_ID = "MovieID"; 
    
    private static final String CREATE_ACTOR = "{ CALL createActor (?,?,?) }";
    private static final String UPDATE_ACTOR = "{ CALL updateActor (?,?,?) }";
    private static final String DELETE_ACTOR = "{ CALL deleteActor (?) }";
    private static final String SELECT_ACTOR = "{ CALL selectActor (?) }";
    private static final String SELECT_ACTORS = "{ CALL selectActorsFromMovie (?) }";
    
    private static final String CREATE_DIRECTOR = "{ CALL createDirector (?,?,?) }";
    private static final String UPDATE_DIRECTOR = "{ CALL updateDirector (?,?,?) }";
    private static final String DELETE_DIRECTOR = "{ CALL deleteDirector (?) }";
    private static final String SELECT_DIRECTOR = "{ CALL selectDirector (?) }";
    private static final String SELECT_DIRECTORS = "{ CALL selectDirectorsFromMovie (?) }";
    
    private static final String CREATE_GENRE = "{ CALL createGenre (?,?,?) }";
    private static final String UPDATE_GENRE = "{ CALL updateGenre (?,?,?) }";
    private static final String DELETE_GENRE = "{ CALL deleteGenre (?) }";
    private static final String SELECT_GENRE = "{ CALL selectGenre (?) }";
    private static final String SELECT_GENRES = "{ CALL selectGenresFromMovie (?) }";

    @Override
    public int createMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
            
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(3, movie.getDescription());
            stmt.setString(4, movie.getOrigName());
            stmt.setInt(5, movie.getTime());
            stmt.setString(6, movie.getPicturePath());
            stmt.setString(7, movie.getLink());
            stmt.setString(8, movie.getGuid());
            stmt.setString(9, movie.getStartDate());
            stmt.registerOutParameter(10, Types.INTEGER);

            stmt.executeUpdate();
            
            createActors(stmt.getInt(10), movie.getActors());
            createDirectors(stmt.getInt(10), movie.getDirectors());
            
            return stmt.getInt(10);
        }
    }

    @Override
    public void createMovies(List<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            for (Movie movie : movies) {
                stmt.setString(1, movie.getTitle());
                stmt.setString(2, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
                stmt.setString(3, movie.getDescription());
                stmt.setString(4, movie.getOrigName());
                stmt.setInt(5, movie.getTime());
                stmt.setString(6, movie.getPicturePath());
                stmt.setString(7, movie.getLink());
                stmt.setString(8, movie.getGuid());
                stmt.setString(9, movie.getStartDate());
                stmt.registerOutParameter(10, Types.INTEGER);
                
                stmt.executeUpdate();
                
                createActors(stmt.getInt(10), movie.getActors());
                createDirectors(stmt.getInt(10), movie.getDirectors());
                createGenres(stmt.getInt(10), movie.getGenres());
            }
        } 
    }

    @Override
    public void updateMovie(int id, Movie data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {
            
            stmt.setString(1, data.getTitle());
            stmt.setString(2, data.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(3, data.getDescription());
            stmt.setString(4, data.getOrigName());
            stmt.setInt(5, data.getTime());
            stmt.setString(6, data.getPicturePath());
            stmt.setString(7, data.getLink());
            stmt.setString(8, data.getGuid());
            stmt.setString(9, data.getStartDate());
            stmt.setInt(10, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
        } 
    }

    @Override
    public Optional<Movie> selectMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {
           
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                            rs.getString(DESCRIPTION),
                            rs.getString(ORIG_NAME),
                            rs.getInt(TIME),
                            rs.getString(PICTURE_PATH),
                            rs.getString(LINK),
                            rs.getString(GUID),
                            rs.getString(START_DATE)));
                            
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIES);
                ResultSet rs = stmt.executeQuery()) {
          
            while (rs.next()) {
                movies.add(new Movie(
                                rs.getInt(ID_MOVIE),
                                rs.getString(TITLE),
                                LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                                rs.getString(DESCRIPTION),
                                rs.getString(ORIG_NAME),
                                rs.getInt(TIME),
                                rs.getString(PICTURE_PATH),
                                rs.getString(LINK),
                                rs.getString(GUID),
                                rs.getString(START_DATE)));
            }
        } 
        return movies;
    }

    @Override
    public int createActor(Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {
            
            stmt.setString(1, actor.getName());
            stmt.setInt(2, actor.getMovieID());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void createActors(int movieId, List<Actor> actors) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {

            for (Actor actor : actors) {
                stmt.setString(1, actor.getName());
                stmt.setInt(2, movieId);
                stmt.registerOutParameter(3, Types.INTEGER);
                
                stmt.executeUpdate();
            }
        } 
    }

    @Override
    public void updateActor(int id, Actor data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_ACTOR)) {
            
            stmt.setString(1, data.getName());
            stmt.setInt(2, data.getMovieID());
            stmt.setInt(3, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteActor(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ACTOR)) {
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Actor> selectActor(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTOR)) {
           
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    return Optional.of(new Actor(
                                rs.getInt(ID_ACTOR),
                                rs.getString(NAME),
                                rs.getInt(MOVIE_ID)));
                            
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Actor> selectActorsFromMovie(Movie movie) throws Exception {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTORS)) {
            
            stmt.setInt(1, movie.getId());
            ResultSet rs = stmt.executeQuery();
          
             while (rs.next()) {
                actors.add(new Actor(
                                rs.getInt(ID_ACTOR),
                                rs.getString(NAME),
                                rs.getInt(MOVIE_ID)));
            }
        } 
        return actors;
    }

    @Override
    public int createDirector(Director actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR)) {
            
            stmt.setString(1, actor.getName());
            stmt.setInt(2, actor.getMovieID());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void createDirectors(int movieId, List<Director> directors) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR)) {

            for (Director actor : directors) {
                stmt.setString(1, actor.getName());
                stmt.setInt(2, movieId);
                stmt.registerOutParameter(3, Types.INTEGER);
                
                stmt.executeUpdate();
            }
        } 
    }

    @Override
    public void updateDirector(int id, Director data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_DIRECTOR)) {
            
            stmt.setString(1, data.getName());
            stmt.setInt(2, data.getMovieID());
            stmt.setInt(3, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_DIRECTOR)) {
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Director> selectDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTOR)) {
           
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    return Optional.of(new Director(
                                rs.getInt(ID_DIRECTOR),
                                rs.getString(NAME),
                                rs.getInt(MOVIE_ID)));
                            
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Director> selectDirectorsFromMovie(Movie movie) throws Exception {
        List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS)) {
            
            stmt.setInt(1, movie.getId());
            ResultSet rs = stmt.executeQuery();
          
             while (rs.next()) {
                directors.add(new Director(
                                rs.getInt(ID_DIRECTOR),
                                rs.getString(NAME),
                                rs.getInt(MOVIE_ID)));
            }
        } 
        return directors;
    }

    @Override
    public int createGenre(Genre actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_GENRE)) {
            
            stmt.setString(1, actor.getName());
            stmt.setInt(2, actor.getMovieID());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void createGenres(int movieId, List<Genre> genres) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_GENRE)) {

            for (Genre genre : genres) {
                stmt.setString(1, genre.getName());
                stmt.setInt(2, movieId);
                stmt.registerOutParameter(3, Types.INTEGER);
                
                stmt.executeUpdate();
            }
        } 
    }

    @Override
    public void updateGenre(int id, Genre data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_GENRE)) {
            
            stmt.setString(1, data.getName());
            stmt.setInt(2, data.getMovieID());
            stmt.setInt(3, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteGenre(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_GENRE)) {
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Genre> selectGenre(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_GENRE)) {
           
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    return Optional.of(new Genre(
                                rs.getInt(ID_GENRE),
                                rs.getString(NAME),
                                rs.getInt(MOVIE_ID)));
                            
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Genre> selectGenresFromMovie(Movie movie) throws Exception {
        List<Genre> genres = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_GENRES)) {
            
            stmt.setInt(1, movie.getId());
            ResultSet rs = stmt.executeQuery();
          
             while (rs.next()) {
                genres.add(new Genre(
                                rs.getInt(ID_GENRE),
                                rs.getString(NAME),
                                rs.getInt(MOVIE_ID)));
            }
        } 
        return genres;
    }
}
