/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.parsers.rss;

import hr.algebra.model.Movie;
import hr.algebra.utils.FileUtils;
import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import hr.algebra.model.Genre;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author dnlbe
 */
public class MovieParser {

    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=1";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";

    private MovieParser() {
    }
    
    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> articles = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
        try (InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);

            Optional<TagType> tagType = Optional.empty();
            Movie movie = null;
            List<String> actorStringList = null;
            List<String> directorStringList = null;
            List<String> genreStringList = null;
            StartElement startElement = null;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (tagType.isPresent()) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            switch (tagType.get()) {
                                case ITEM:
                                    movie = new Movie();
                                    articles.add(movie);
                                    break;
                                case TITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                    break;
                                case PUB_DATE:
                                    if (movie != null && !data.isEmpty()) {
                                        LocalDateTime publishedDate = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
                                        movie.setPublishedDate(publishedDate);
                                    }
                                    break;
                                case DESCRIPTION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDescription(data);
                                    }
                                    break;
                                case ORIG_NAZIV:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setOrigName(data);
                                    }
                                    break;
                                case REDATELJ:
                                    if (movie != null && !data.isEmpty()) {
                                        directorStringList = Arrays.asList(data.split("\\s*,\\s*"));
                                        List<Director> movieDirectors = new ArrayList<>();
                                        
                                        for (String directorString : directorStringList)
                                        {
                                            Director director = new Director();
                                            director.setName(directorString);
                                            movieDirectors.add(director);
                                        }
                                        
                                        movie.setDirectors(movieDirectors);
                                    }
                                    break;
                                case GLUMCI:
                                    if (movie != null && !data.isEmpty()) {
                                        actorStringList = Arrays.asList(data.split("\\s*,\\s*"));
                                        List<Actor> movieActors = new ArrayList<>();
                                        
                                        for (String actorString : actorStringList)
                                        {
                                            Actor actor = new Actor();
                                            actor.setName(actorString);
                                            movieActors.add(actor);
                                        }
                                        
                                        movie.setActors(movieActors);
                                    }
                                    break;
                                case TRAJANJE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setTime(Integer. parseInt(data));
                                    }
                                    break;
                                case ZANR:
                                    if (movie != null && !data.isEmpty()) {
                                        genreStringList = Arrays.asList(data.split("\\s*,\\s*"));
                                        List<Genre> movieGenres = new ArrayList<>();
                                        
                                        for (String genreString : genreStringList)
                                        {
                                            Genre genre = new Genre();
                                            genre.setName(genreString);
                                            movieGenres.add(genre);
                                        }
                                        
                                        movie.setGenres(movieGenres);
                                    }
                                    break;
                                case PLAKAT: //IMAGE
                                    // bugfix -> prevent to enter 2 times!!!
                                    if (movie != null && startElement != null && movie.getPicturePath() == null) {
                                        handlePicture(movie, data);
                                    }
                                    break;
                                case LINK:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setLink(data);
                                    }
                                    break;
                                case GUID:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setGuid(data);
                                    }
                                    break;
                                case POCETAK:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setStartDate(data);
                                    }
                                    break;
                            }
                        }
                        break;
                }
            }
        }
        return articles;

    }

    private static void handlePicture(Movie article, String pictureUrl){

        try {
            String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXT;
            }
            String pictureName = UUID.randomUUID() + ext;
            String localPicturePath = DIR + File.separator + pictureName;
            
            FileUtils.copyFromUrl(pictureUrl, localPicturePath);
            article.setPicturePath(localPicturePath);
        } catch (IOException ex) {
            Logger.getLogger(MovieParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private enum TagType {

        ITEM("item"),
        TITLE("title"),
        LINK("link"),
        DESCRIPTION("description"),
        ENCLOSURE("enclosure"),
        PUB_DATE("pubDate"),
        REDATELJ("redatelj"),
        GLUMCI("glumci"),
        TRAJANJE("trajanje"),
        ZANR("zanr"),
        PLAKAT("plakat"),
        GUID("guid"),
        POCETAK("pocetak"),
        ORIG_NAZIV("orignaziv");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }

}
