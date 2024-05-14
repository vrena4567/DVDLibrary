package org.example.ui;

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

    public void displayExitMessage(){
        io.print("Good bye!");
    }

    public void displayUnknownCommand(){
        io.print("Unknown Command!");
    }

}
