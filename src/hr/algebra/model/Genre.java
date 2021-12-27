/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

/**
 *
 * @author brand
 */
public class Genre {
    private int IDGenre;
    private String Name;
    private int MovieID;
    
    public Genre(String Name, int MovieID) {
        this.Name = Name;
        this.MovieID = MovieID;
    }
    
    public Genre(int IDDirector, String Name, int MovieID) {
        this(Name, MovieID);
        this.IDGenre = IDDirector;
    }
    
    public Genre() {
    }

    public int getIDGenre() {
        return IDGenre;
    }

    public void setIDGenre(int IDGenre) {
        this.IDGenre = IDGenre;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int MovieID) {
        this.MovieID = MovieID;
    }
    
    @Override
    public String toString() {
        return Name;
    }
}
