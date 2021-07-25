package main.transactions;

import lombok.NoArgsConstructor;
import main.initialization.InitializeValues;
import main.inventory.RentalTool;
import main.report.agreements.RentalAgreement;
import main.report.consoleUtilites.ConsoleUtilites;
import static main.report.styles.MenuStyle.agreementNotReady;
import static main.report.styles.MenuStyle.agreementReady;
import static main.report.styles.MenuStyle.agreementValueNotSet;
import static main.report.styles.MenuStyle.border;
import static main.report.styles.MenuStyle.columnWidthLargeIndented;
import static main.report.styles.MenuStyle.columnWidthLargeIndentedWithPercent;
import static main.report.styles.MenuStyle.toolMenuColumns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Map;

@NoArgsConstructor(force = true)
public class TransactionRentTool {

    private DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .appendPattern("[M/dd/uuuu]").appendPattern("[M/d/uuuu]")
            .appendPattern("[MM/dd/uuuu]").appendPattern("[MM/d/uuuu]")
            .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
            .toFormatter();

    private RentalTool rentalTool = new RentalTool();
    private Integer rentalDayCount = 0;
    private Integer discountPercent = 0;
    private LocalDate checkoutDate;
    private boolean toolSelected = false;
    private boolean numberOfDaysSelected = false;
    private boolean checkoutDateEntered = false;
    private static int discountLowestPercent = 0;
    private static int discountHighestPercent = 100;
    private static int minNumberOfRentalDays = 0;
    private static int maxNumberOfRentalDays = (int) (365.25*5);

    InputStreamReader in = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(in);

    public void processRental(InitializeValues initializeValues) {
        RentalAgreement rentalAgreement = new RentalAgreement();
        RentalTotals rentalTotals = new RentalTotals();

        String userResponse = "";
        do {
            try{
                boolean readyForPreview = toolSelected && numberOfDaysSelected && checkoutDateEntered;
                rentalMenuOptions(initializeValues, rentalTool.getToolCode(), rentalDayCount, discountPercent, checkoutDate, readyForPreview);

                userResponse = br.readLine();
                switch(userResponse){
                    case "1":
                        rentalTool = selectToolToRent(initializeValues.RentalToolInventory);
                        toolSelected = (null != rentalTool.getToolCode()) ? true : false;
                        break;
                    case "2":
                        rentalDayCount = selectRentalDays();
                        numberOfDaysSelected = rentalDayCount > 0;
                        break;
                    case "3":
                        discountPercent = selectDiscount();
                        break;
                    case "4":
                        checkoutDate = selectCheckOutDate();
                        checkoutDateEntered = isValidDate(checkoutDate);
                        break;
                    case "5":
                        if (readyForPreview){
                            rentalAgreement.printAgreement(
                                    rentalTotals.calculateRentalValues(initializeValues, rentalTool, rentalDayCount, discountPercent, checkoutDate));
                        }
                        break;
                    case "9":
                        resetValues();
                    default:
                }
            } catch(Exception ex){
                System.out.println("\n\nPlease provide a valid entry.");
            }
        } while (!userResponse.toLowerCase().startsWith("0"));
    }

    private void rentalMenuOptions(InitializeValues initializeValues, String toolSelected, int numberOfDaysSelected,
                                       int discount, LocalDate checkoutDate, boolean readyForPreview) {

        String readyToPrintAgreement = readyForPreview ? agreementReady : agreementNotReady;
        String toolReady = toolSelected.length()>0 ? toolSelected : agreementValueNotSet;
        String daysReady = numberOfDaysSelected > 0 ? Integer.toString(numberOfDaysSelected) : agreementValueNotSet;
        String checkoutDateReady = null != checkoutDate ? checkoutDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) : agreementValueNotSet;

        ConsoleUtilites.clearWindow();
        System.out.format("\n\n\n\n");
        System.out.format(border);
        System.out.println("Demo Point of Sale Project.\nTool Rentals");
        System.out.println("Please select one of the following options:");
        System.out.format(columnWidthLargeIndented, "1) Select Tool to Rent", toolReady);
        System.out.format(columnWidthLargeIndented, "2) Enter Number of Days to Rent", daysReady);
        System.out.format(columnWidthLargeIndentedWithPercent, "3) Enter Discount", discount);
        System.out.format(columnWidthLargeIndented, "4) Enter Checkout Date", checkoutDateReady);
        System.out.format(columnWidthLargeIndented, "5) Review Rental Agreement", readyToPrintAgreement);
        System.out.format(columnWidthLargeIndented, "9) Clear entered values / start new rental", "");
        System.out.format(columnWidthLargeIndented, "0) Return to Main Menu", "");
        System.out.format(border);
    }

    private RentalTool selectToolToRent(Map<String, RentalTool> rentalTools) throws Exception {
        String userResponse = "";
        RentalTool toolToRent;
            printToolList(rentalTools);
            do {
                try {
                    if ("?".equals(userResponse)) {
                        printToolList(rentalTools);
                    }
                    System.out.println("\r\nEnter the Tool Code of the tool you wish to rent.");
                    System.out.println("Or enter ? to print the tool list: ");
                    userResponse = br.readLine();
                    if (validateRentalTool(rentalTools, userResponse)){
                        return rentalTools.get(userResponse.toUpperCase());
                    }
                } catch(Exception ex){
                    System.out.println("\r\nPlease enter a valid tool code.");
                }
            } while(true);
    }

    private void printToolList(Map<String, RentalTool> rentalTools){
        System.out.println("\r\nTOOL LIST");
        System.out.format(border);
        System.out.format(toolMenuColumns, "Tool code", "Tool type", "Tool brand");

        for (Map.Entry<String,RentalTool> entry : rentalTools.entrySet()) {
            System.out.format(toolMenuColumns, entry.getKey(),
                    entry.getValue().getToolType(), entry.getValue().getToolBrand());
        }
        System.out.format(border);
    }

    private int selectRentalDays() throws Exception {
        String userResponse = "";
        int numberOfDays = 0;

        do {
            System.out.println("Enter number of days of rental: ");
            try {
                userResponse = br.readLine();
                numberOfDays = Integer.parseInt(userResponse);
                if(validateRentalDayCount(numberOfDays)){
                    return numberOfDays;
                }
            } catch (Exception ex){
                System.out.println("Please enter a value greater than 0");
            }
        } while(true);
    }

    private int selectDiscount() throws Exception {
        String userResponse;
        int discountPercentage = 0;

        do {
            System.out.println("Enter amount of discount, between " + discountLowestPercent + " and " + discountHighestPercent + " : ");
            try {
                userResponse = br.readLine();
                discountPercentage = Integer.parseInt(userResponse);
                if(validateDiscountPercentage(discountPercentage)) {
                    return discountPercentage;
                }
            } catch (Exception ex) {
                System.out.println("Please enter a value between "+ discountLowestPercent +" and " + discountHighestPercent + " : ");
            }
        } while (true);
    }

    private LocalDate selectCheckOutDate() throws Exception {
        String userResponse;

        boolean useTodaysDate = questionYesOrNo("Are you checking out the equipment today? (Y/N): ");
        if(useTodaysDate) {
            checkoutDate = LocalDate.now();
        } else {
            System.out.println("Enter checkout date, format is mm/dd/yyyy: ");
            do {
                try {
                    userResponse = br.readLine();
                    checkoutDate = LocalDateTime.parse(userResponse, dateTimeFormatter).toLocalDate();
                } catch (Exception ex) {
                    System.out.println("Please enter a valid date value, format is mm/dd/yyyy: ");
                    checkoutDate = null;
                }
            } while (!isValidDate(checkoutDate));
        }
        return checkoutDate;
    }

    private boolean questionYesOrNo(String question) throws IOException {
        String userResponse;
        do {
            System.out.println(question);
            userResponse = br.readLine();
        } while(
                //(userResponse.trim().length() < 1) &&
                !(userResponse.toLowerCase().startsWith("n") ||
                  userResponse.toLowerCase().startsWith("y")));

        return userResponse.toLowerCase().startsWith("y");
    }

    public static boolean validateRentalDayCount(int numberOfDays) throws Exception {
        if(numberOfDays > minNumberOfRentalDays && numberOfDays <= maxNumberOfRentalDays){
            return true;
        } else {
             throw new Exception("Invalid Number of Rental Days Entered");
        }
    }

    public static boolean validateRentalTool(Map<String, RentalTool> rentalTools, String userResponse) throws Exception {
        if (rentalTools.containsKey(userResponse.toUpperCase())){
            return true;
        } else {
            throw new Exception("Invalid Rental Tool Selected");
        }
    }

    public static boolean validateDiscountPercentage(int discountPercentage) throws Exception {
        if (discountPercentage >= discountLowestPercent && discountPercentage <= discountHighestPercent){
            return true;
        } else {
            throw new Exception("Invalid Discount Percentage");
        }
    }

    public static boolean isValidDate(LocalDate localDate) throws Exception {
        if (null != LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth())){
            return true;
        } else {
            throw new Exception("Invalid Date");
        }
    }

    private void resetValues(){
        rentalTool = new RentalTool();
        rentalDayCount = 0;
        discountPercent = 0;
        checkoutDate = null;
        toolSelected = false;
        numberOfDaysSelected = false;
        checkoutDateEntered = false;
    }

}