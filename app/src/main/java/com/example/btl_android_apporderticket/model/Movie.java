package com.example.btl_android_apporderticket.model;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private String movieId;
    private String title;
    private String language;
    private String country;
    private String description;
    private String releasedDate;
    private String director;
    private int duration;
    private String poster;
    private int ageRating;
    private int genreId;
    private List<String> casts;
    private List<String> idCinemas;

    public Movie() {
    }

    public Movie(String movieId, String title, String language, String country, String description, String releasedDate, String director, int duration, String poster, int ageRating, int genreId, List<String> casts, List<String> idCinemas) {
        this.movieId = movieId;
        this.title = title;
        this.language = language;
        this.country = country;
        this.description = description;
        this.releasedDate = releasedDate;
        this.director = director;
        this.duration = duration;
        this.poster = poster;
        this.ageRating = ageRating;
        this.genreId = genreId;
        this.casts = casts;
        this.idCinemas = idCinemas;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public List<String> getCasts() {
        return casts;
    }

    public void setCasts(List<String> casts) {
        this.casts = casts;
    }

    public List<String> getIdCinemas() {
        return idCinemas;
    }

    public void setIdCinemas(List<String> idCinemas) {
        this.idCinemas = idCinemas;
    }

    @Override
    public String toString() {
        return "Movie{" + "movieId='" + movieId + '\'' + ", title='" + title + '\'' + ", language='" + language + '\'' + ", country='" + country + '\'' + ", description='" + description + '\'' + ", releasedDate='" + releasedDate + '\'' + ", director='" + director + '\'' + ", duration=" + duration + ", poster='" + poster + '\'' + ", ageRating=" + ageRating + ", genreId=" + genreId + ", casts=" + casts + ", idCinemas=" + idCinemas + '}';
    }
}
