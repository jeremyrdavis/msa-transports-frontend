package io.arrogantprogrammer.domain;

import java.time.LocalDate;

public class Film {

    private String title;
    private Integer episodeID;
    private String director;
    private LocalDate releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisodeID() {
        return episodeID;
    }

    public void setEpisodeID(Integer episodeID) {
        this.episodeID = episodeID;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }


    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", episodeID=" + episodeID +
                ", director='" + director + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}