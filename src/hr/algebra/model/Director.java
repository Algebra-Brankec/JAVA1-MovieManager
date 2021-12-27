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
public class Director {
    private int IDDirector;
    private String Name;
    private int MovieID;
    
    public Director(String Name, int MovieID) {
        this.Name = Name;
        this.MovieID = MovieID;
    }
    
    public Director(int IDDirector, String Name, int MovieID) {
        this(Name, MovieID);
        this.IDDirector = IDDirector;
    }
    
    public Director() {
    }

    public int getIDDirector() {
        return IDDirector;
    }

    public void setIDDirector(int IDDirector) {
        this.IDDirector = IDDirector;
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
