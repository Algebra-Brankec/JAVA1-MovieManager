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
public class Actor {
    private int IDActor;
    private String Name;
    private int MovieID;

    public Actor(String Name, int MovieID) {
        this.Name = Name;
        this.MovieID = MovieID;
    }
    
    public Actor(int IDActor, String Name, int MovieID) {
        this(Name, MovieID);
        this.IDActor = IDActor;
    }
    
    public Actor() {
    }

    public int getIDActor() {
        return IDActor;
    }

    public void setIDActor(int IDActor) {
        this.IDActor = IDActor;
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
