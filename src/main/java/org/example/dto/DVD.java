package org.example.dto;

import java.time.LocalDate;

public class DVD {
    private String dvdId;
    private String title;
    private LocalDate releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userNote;
    public String getDvdId(){
        return dvdId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        LocalDate ldReleaseDate = LocalDate.parse(releaseDate);
        this.releaseDate = ldReleaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
}
