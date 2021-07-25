package main.initialization;

import main.report.consoleUtilites.ConsoleUtilites;
import main.report.styles.MenuStyle;
import main.transactions.TransactionRentTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class mainMenu {

    InputStreamReader in = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(in);

    public void mainMenu(InitializeValues initializeValuesValues) throws IOException {
        String userResponse;
        do {
            mainMenuOptions();
            userResponse = br.readLine();
            switch(userResponse){
                case "1":
                    processRental(initializeValuesValues);
                default:
            }
        } while (!userResponse.toLowerCase().startsWith("0"));
    }

    public void processRental(InitializeValues initializeValues) {
        TransactionRentTool transactionRentTool = new TransactionRentTool();
        transactionRentTool.processRental(initializeValues);
    }

    private void mainMenuOptions() {
        ConsoleUtilites.clearWindow();
        System.out.println("\n\n\n\n");
        System.out.format(MenuStyle.border, "");
        System.out.println("Welcome to the Demo Point of Sale Project.");
        System.out.println("Please select one of the following options:");
        System.out.println("\t1) Tool Reservation");
        System.out.println("\t0) Exit Program");
        System.out.format(MenuStyle.border, "");

    }

}
