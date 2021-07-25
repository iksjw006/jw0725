package main.report.agreements;

import main.calendar.CalendarCalculations;
import main.report.consoleUtilites.ConsoleUtilites;
import main.report.styles.MenuStyle;
import static main.report.styles.MenuStyle.columnWidthSmallCurrency;
import static main.report.styles.MenuStyle.columnWidthSmallDate;
import static main.report.styles.MenuStyle.columnWidthSmallInteger;
import static main.report.styles.MenuStyle.columnWidthSmallPercent;
import static main.report.styles.MenuStyle.columnWidthSmallString;
import main.transactions.RentalTotals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RentalAgreement {

    InputStreamReader in = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(in);

    public void printAgreement(RentalTotals rentalTotals) throws IOException {

        ConsoleUtilites.clearWindow();
        System.out.format("\n\n\n\n");
        System.out.format(MenuStyle.border);
        System.out.format("RENTAL AGREEMENT\n");
        System.out.format(MenuStyle.border);
        System.out.format(columnWidthSmallString,   "Tool code", rentalTotals.getRentalTool().getToolCode());
        System.out.format(columnWidthSmallString,   "Tool type", rentalTotals.getRentalTool().getToolType());
        System.out.format(columnWidthSmallString,   "Tool brand", rentalTotals.getRentalTool().getToolBrand());
        System.out.format(columnWidthSmallString,   "Rental days", rentalTotals.getRentalDayCount());
        System.out.format(columnWidthSmallDate,     "Checkout date", rentalTotals.getCheckoutDate());
        System.out.format(columnWidthSmallDate,     "Due date", rentalTotals.getCheckoutDate().plusDays(rentalTotals.getRentalDayCount()));
        System.out.format(columnWidthSmallCurrency, "Daily rental charge", rentalTotals.getRentalTool().getRentalPrice());
        System.out.format(columnWidthSmallInteger,  "Charge days", rentalTotals.getChargedDays());
        System.out.format(columnWidthSmallCurrency, "Pre-discount charge", rentalTotals.getPreDiscountChargedAmount());
        System.out.format(columnWidthSmallPercent,  "Discount percent", rentalTotals.getDiscountPercent());
        System.out.format(columnWidthSmallCurrency, "Discount amount",  rentalTotals.getDiscountAmount());
        System.out.format(columnWidthSmallCurrency, "Final charge", rentalTotals.getFinalChargedAmount());
        System.out.format(MenuStyle.border);

        System.out.println("\nPress Enter or Return key to continue.");
        br.readLine();
    }

}
