package org.example.ui;

import org.example.dto.DVD;

import java.util.List;

public class View {
    private UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public void displayMenu() {
        io.print("==== DVD Collection Menu ====");
        io.print("1. Add new DVD");
        io.print("2. Remove DVD");
        io.print("3. Edit existing DVD");
        io.print("4. List All DVDs");
        io.print("5. Search DVD by ID");
        io.print("6. Search DVD by title");
        io.print("7. Exit");
    }

    public int getMenuSelection() {
        return io.readInt("Please select from the choices above.", 1, 7);
    }

    public DVD getNewDvdInfo() {
        String dvdID = io.readString("Please enter DVD id");
        String title = io.readString("Please enter DVD title");
        String releaseDate = io.readString("Please enter release date (format type: yyyy-MM-dd)");
        String mpaaRating = io.readString("Please enter MPAArating");
        String directorName = io.readString("Please enter director name");
        String studio = io.readString("Please enter studio name");
        String userNote = io.readString("Please enter additional information, user rating, etc.");
        DVD currentDvd = new DVD(dvdID);
        currentDvd.setTitle(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserNote(userNote);
        return currentDvd;
    }

    public void displayExitMessage() {
        io.print("GOOD BYE!");
    }

    public void displayUnknownCommand() {
        io.print("Unknown Command!");
    }

    public void displaySuccessBannerNewDvd() {
        io.print("==== DVD Successfully Saved ====");
    }

    public void displayListBanner() {
        io.print("==== All DVDs in the Collection ====");
    }

    public void displayAllDvds(List<DVD> listDvd){
        for(DVD dvd : listDvd){
            io.print(dvd.getDvdId() + " - " + dvd.getTitle() +
                    "\n Release date: " + dvd.getReleaseDate() +
                    "\n MPAA rating: " + dvd.getMpaaRating() +
                    "\n Director's name: " + dvd.getDirectorName() +
                    "\n Studio: " + dvd.getStudio() +
                    "\n Personal note: " + '"' + dvd.getUserNote() + '"');
        }
    }

    public void displayDeleteBanner() {
        io.print("==== Deleting a DVD ====");
    }

    public String deleteDVDById() {
        return io.readString("Please enter a DVD ID you want to delete: ");
    }

    public void displaySuccessDeletionBanner(DVD dvd) {
        io.print("==== " + dvd.getTitle() + " Successfully Deleted ====");
    }

    public void displayEditBanner() {
        io.print("==== Edit DVD ====");
    }

    public String getDvdIdForEdit() {
        return io.readString("Please enter DVD ID that you want to edit: ");
    }

    public int getFieldToEdit() {
        io.print("1. Title" +
                "\n 2. Release date " +
                "\n 3. MPAA rating " +
                "\n 4. Director's name " +
                "\n 5. Studio " +
                "\n 6. Personal note ");
        return io.readInt("Which field do you want to edit?", 1, 6);
    }

    public String getNewInfoForDVDField() {
        return io.readString("Please enter the modified info of the selected field");
    }

    public void displayEditSuccesBanner() {
        io.print("==== DVD Succesfully Edited ====");
    }

    public void displaySearchByIdBanner() {
        io.print("==== DVD Search By ID ====");
    }

    public String getDvdIdForSearch(){
        return io.readString("Please enter the DVD ID that you want to search for: ");
    }

    public void displayDVD(DVD dvd) {
        io.print(dvd.getDvdId() + " - " + dvd.getTitle() +
                "\n Release date: " + dvd.getReleaseDate() +
                "\n MPAA rating: " + dvd.getMpaaRating() +
                "\n Director's name: " + dvd.getDirectorName() +
                "\n Studio: " + dvd.getStudio() +
                "\n Personal note: " + '"' + dvd.getUserNote() + '"');
    }

    public void displaySearchByTitleBanner() {
        io.print("==== Search DVD By Title ====");
    }

    public String getDVDTitle() {
        return io.readString("Please enter the title of the DVD you want to search for: ");
    }
}
