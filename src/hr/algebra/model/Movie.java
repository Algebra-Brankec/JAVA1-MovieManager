/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dnlbe
 */
public class Movie {
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private int id;
    private String title;
    private LocalDateTime publishedDate;
    private String description;
    private String origName;
    private List<Director> directors = new ArrayList<>();;
    private List<Actor> actors = new ArrayList<>();
    private int time;
    private List<Genre> genres;
    private String picturePath; //poster
    private String link;
    private String guid;
    private String startDate;
    
    public Movie(String title, LocalDateTime publishedDate, String description, String origName, int time, String picturePath, String link, String guid, String startDate ) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.picturePath = picturePath;
        this.publishedDate = publishedDate;
        this.origName = origName;
        this.time = time;
        this.guid = guid;
        this.startDate = startDate;
    }
    
    public Movie(int id, String title, LocalDateTime publishedDate, String description, String origName, int time, String picturePath, String link, String guid, String startDate) {
        this(title, publishedDate, description, origName, time, picturePath, link, guid, startDate );
        this.id = id;
    }

    public String getOrigName() {
        return origName;
    }

    public void setOrigName(String origName) {
        this.origName = origName;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genre) {
        this.genres = genre;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String start) {
        this.startDate = start;
    }
    
    public Movie() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return id + " - " + title;
    }
    
}
