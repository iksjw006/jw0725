package tests.rental;

import main.initialization.InitializeValues;
import main.inventory.RentalTool;
import main.transactions.RentalTotals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class toolRentalComponentTests {

    @Test
    public void given_A_Tool_Rental_when_ToolCode_Chainsaw_CheckoutDate_PastSeptember_RentalDays_5_Discount_101_then_Fail() throws Exception{

        String toolCode = "JAKR";
        LocalDate checkoutDate = LocalDate.of(2015, Month.SEPTEMBER, 3);
        int rentalDays = 5;
        int discountPercent = 101;

        InitializeValues initializeValues = new InitializeValues();
        RentalTool rentalTool = new RentalTool();
        rentalTool = initializeValues.RentalToolInventory.get(toolCode);

        RentalTotals rentalTotals = new RentalTotals();
        rentalTotals = rentalTotals.calculateRentalValues(initializeValues, rentalTool, rentalDays, discountPercent,checkoutDate);

        assertEquals("java.lang.Exception: Invalid Discount Percentage", rentalTotals.getErrorMessage());

    }

    @Test
    public void given_A_Tool_Rental_when_ToolCode_Ladder_CheckoutDate_PastJulyHoliday_RentalDays_3_Discount_10_then_Pass() throws Exception{

        String toolCode = "LADW";
        LocalDate checkoutDate = LocalDate.of(2020, Month.JULY, 2);
        int rentalDays = 3;
        int discountPercent = 10;

        InitializeValues initializeValues = new InitializeValues();
        RentalTool rentalTool = new RentalTool();
        rentalTool = initializeValues.RentalToolInventory.get(toolCode);

        RentalTotals rentalTotals = new RentalTotals();
        rentalTotals = rentalTotals.calculateRentalValues(initializeValues, rentalTool, rentalDays, discountPercent,checkoutDate);

        assertEquals(null, rentalTotals.getErrorMessage());
        assertEquals(rentalTotals.getChargedDays(), 2);
        assertEquals(String.format("%.2f", rentalTotals.getDiscountAmount()), "0.40");
        assertEquals(String.format("%.2f", rentalTotals.getFinalChargedAmount()), "3.58");
        assertEquals(String.format("%.2f", rentalTool.getRentalPrice()), "1.99");
        assertEquals(rentalTool.getToolType(), "Ladder");
        assertEquals(rentalTool.getToolBrand(), "Werner");

    }

    @Test
    public void given_A_Tool_Rental_when_ToolCode_Chainsaw_CheckoutDate_PastJulyHoliday_RentalDays_5_Discount_25_then_Pass() throws Exception{

        String toolCode = "CHNS";
        LocalDate checkoutDate = LocalDate.of(2015, Month.JULY, 2);
        int rentalDays = 5;
        int discountPercent = 25;

        InitializeValues initializeValues = new InitializeValues();
        RentalTool rentalTool = new RentalTool();
        rentalTool = initializeValues.RentalToolInventory.get(toolCode);

        RentalTotals rentalTotals = new RentalTotals();
        rentalTotals = rentalTotals.calculateRentalValues(initializeValues, rentalTool, rentalDays, discountPercent,checkoutDate);

        assertEquals(null, rentalTotals.getErrorMessage());
        assertEquals(rentalTotals.getChargedDays(), 3);
        assertEquals(String.format("%.2f", rentalTotals.getDiscountAmount()), "1.12");
        assertEquals(String.format("%.2f", rentalTotals.getFinalChargedAmount()), "3.35");
        assertEquals(String.format("%.2f", rentalTool.getRentalPrice()), "1.49");
        assertEquals(rentalTool.getToolType(), "Chainsaw");
        assertEquals(rentalTool.getToolBrand(), "Stihl");

    }

    @Test
    public void given_A_Tool_Rental_when_ToolCode_Jackhammer_CheckoutDate_SeptemberHoliday_RentalDays_6_Discount_0_then_Pass() throws Exception{

        String toolCode = "JAKD";
        LocalDate checkoutDate = LocalDate.of(2015, Month.SEPTEMBER, 3);
        int rentalDays = 6;
        int discountPercent = 0;

        InitializeValues initializeValues = new InitializeValues();
        RentalTool rentalTool = new RentalTool();
        rentalTool = initializeValues.RentalToolInventory.get(toolCode);

        RentalTotals rentalTotals = new RentalTotals();
        rentalTotals = rentalTotals.calculateRentalValues(initializeValues, rentalTool, rentalDays, discountPercent,checkoutDate);

        assertEquals(null, rentalTotals.getErrorMessage());
        assertEquals(rentalTotals.getChargedDays(), 3);
        assertEquals(String.format("%.2f", rentalTotals.getDiscountAmount()), "0.00");
        assertEquals(String.format("%.2f", rentalTotals.getFinalChargedAmount()), "8.97");
        assertEquals(String.format("%.2f", rentalTool.getRentalPrice()), "2.99");
        assertEquals(rentalTool.getToolType(), "Jackhammer");
        assertEquals(rentalTool.getToolBrand(), "DeWalt");
        assertEquals(rentalTool.isChargeHoliday(), false);
        assertEquals(rentalTool.isChargeWeekday(), true);
        assertEquals(rentalTool.isChargeWeekend(), false);

    }

    @Test
    public void given_A_Tool_Rental_when_ToolCode_Jackhammer_CheckoutDate_JulyHoliday_RentalDays_9_Discount_0_then_Pass() throws Exception{

        String toolCode = "JAKR";
        LocalDate checkoutDate = LocalDate.of(2015, Month.JULY, 2);
        int rentalDays = 9;
        int discountPercent = 0;

        InitializeValues initializeValues = new InitializeValues();
        RentalTool rentalTool = new RentalTool();
        rentalTool = initializeValues.RentalToolInventory.get(toolCode);

        RentalTotals rentalTotals = new RentalTotals();
        rentalTotals = rentalTotals.calculateRentalValues(initializeValues, rentalTool, rentalDays, discountPercent,checkoutDate);

        assertEquals(null, rentalTotals.getErrorMessage());
        assertEquals(rentalTotals.getChargedDays(), 6);
        assertEquals(String.format("%.2f", rentalTotals.getDiscountAmount()), "0.00");
        assertEquals(String.format("%.2f", rentalTotals.getFinalChargedAmount()), "17.94");
        assertEquals(String.format("%.2f", rentalTool.getRentalPrice()), "2.99");
        assertEquals(rentalTool.getToolType(), "Jackhammer");
        assertEquals(rentalTool.getToolBrand(), "Ridgid");
        assertEquals(rentalTool.isChargeHoliday(), false);
        assertEquals(rentalTool.isChargeWeekday(), true);
        assertEquals(rentalTool.isChargeWeekend(), false);

    }

    @Test
    public void given_A_Tool_Rental_when_ToolCode_Jackhammer_CheckoutDate_JulyHoliday_RentalDays_4_Discount_50_then_Pass() throws Exception{

        String toolCode = "JAKR";
        LocalDate checkoutDate = LocalDate.of(2020, Month.JULY, 2);
        int rentalDays = 4;
        int discountPercent = 50;

        InitializeValues initializeValues = new InitializeValues();
        RentalTool rentalTool = new RentalTool();
        rentalTool = initializeValues.RentalToolInventory.get(toolCode);

        RentalTotals rentalTotals = new RentalTotals();
        rentalTotals = rentalTotals.calculateRentalValues(initializeValues, rentalTool, rentalDays, discountPercent,checkoutDate);

        assertEquals(null, rentalTotals.getErrorMessage());
        assertEquals(rentalTotals.getChargedDays(), 1);
        assertEquals(String.format("%.2f", rentalTotals.getDiscountAmount()), "1.50");
        assertEquals(String.format("%.2f", rentalTotals.getFinalChargedAmount()), "1.49");
        assertEquals(String.format("%.2f", rentalTool.getRentalPrice()), "2.99");
        assertEquals(rentalTool.getToolType(), "Jackhammer");
        assertEquals(rentalTool.getToolBrand(), "Ridgid");
        assertEquals(rentalTool.isChargeHoliday(), false);
        assertEquals(rentalTool.isChargeWeekday(), true);
        assertEquals(rentalTool.isChargeWeekend(), false);

    }

}
