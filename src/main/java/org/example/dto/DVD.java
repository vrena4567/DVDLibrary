package org.example.dto;

import java.time.LocalDate;
import java.util.Objects;

public class DVD {
    private String dvdId;
    private String title;
    private LocalDate releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userNote;

    public DVD(String dvdId){
        this.dvdId = dvdId;
    }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DVD dvd = (DVD) o;
        return Objects.equals(dvdId, dvd.dvdId) && Objects.equals(title, dvd.title) && Objects.equals(releaseDate, dvd.releaseDate) && Objects.equals(mpaaRating, dvd.mpaaRating) && Objects.equals(directorName, dvd.directorName) && Objects.equals(studio, dvd.studio) && Objects.equals(userNote, dvd.userNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dvdId, title, releaseDate, mpaaRating, directorName, studio, userNote);
    }

    @Override
    public String toString() {
        return "DVD{" +
                "dvdId='" + dvdId + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", mpaaRating='" + mpaaRating + '\'' +
                ", directorName='" + directorName + '\'' +
                ", studio='" + studio + '\'' +
                ", userNote='" + userNote + '\'' +
                '}';
    }
}
