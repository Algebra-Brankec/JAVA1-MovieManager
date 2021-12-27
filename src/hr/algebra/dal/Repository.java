/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dnlbe
 */
public interface Repository {
    
    int createMovie(Movie article) throws Exception;
    void createMovies(List<Movie> articles) throws Exception;    
    void updateMovie(int id, Movie data) throws Exception;
    void deleteMovie(int id) throws Exception;
    Optional<Movie> selectMovie(int id) throws Exception;
    List<Movie> selectMovies() throws Exception;
    
    int createActor(Actor actor) throws Exception;
    void createActors(int movieId, List<Actor> actors) throws Exception;    
    void updateActor(int id, Actor data) throws Exception;
    void deleteActor(int id) throws Exception;
    Optional<Actor> selectActor(int id) throws Exception;
    List<Actor> selectActorsFromMovie(Movie movie) throws Exception;
    
    int createDirector(Director director) throws Exception;
    void createDirectors(int movieId, List<Director> directors) throws Exception;    
    void updateDirector(int id, Director data) throws Exception;
    void deleteDirector(int id) throws Exception;
    Optional<Director> selectDirector(int id) throws Exception;
    List<Director> selectDirectorsFromMovie(Movie movie) throws Exception;
    
    int createGenre(Genre genre) throws Exception;
    void createGenres(int movieId, List<Genre> genres) throws Exception;    
    void updateGenre(int id, Genre data) throws Exception;
    void deleteGenre(int id) throws Exception;
    Optional<Genre> selectGenre(int id) throws Exception;
    List<Genre> selectGenresFromMovie(Movie movie) throws Exception;
}
