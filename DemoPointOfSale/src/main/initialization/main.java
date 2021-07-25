package main.initialization;

import java.io.IOException;

public class main {

    private static InitializeValues initializeValues = new InitializeValues();
    private static mainMenu mainMenu = new mainMenu();

    public static void main(String args[]) throws IOException {
        mainMenu.mainMenu(initializeValues);
        shutdown();
    }

    private static void shutdown(){
        initializeValues.RentalToolInventory = null;
        mainMenu = null;
        initializeValues = null;
    }
}
