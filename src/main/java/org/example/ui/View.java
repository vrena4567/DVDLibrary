package org.example.ui;

import org.example.dto.DVD;

public class View {
    private UserIO io;

    public View(UserIO io){
        this.io = io;
    }
    public void displayMenu(){
        io.print("==== DVD Collection Menu ====");
        io.print("1. Add new DVD");
        io.print("2. Remove DVD");
        io.print("3. Edit existing DVD");
        io.print("4. List All DVDs");
        io.print("5. Search DVD by ID");
        io.print("6. Search DVD by title");
        io.print("7. Exit");
    }

    public int getMenuSelection(){
        return io.readInt("Please select from the choices above.", 1, 7);
    }

    public DVD getNewDvdInfo(){
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
    public void displayExitMessage(){
        io.print("Good bye!");
    }

    public void displayUnknownCommand(){
        io.print("Unknown Command!");
    }

    public void displaySuccessBannerNewDvd(){
        io.print("==== DVD Successfully Saved ====");
    }

    public void displayListBanner(){
        io.print("==== All DVDs in the Collection ====");
    }

}
