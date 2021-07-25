package main.transactions;

import lombok.Getter;
import main.calendar.CalendarCalculations;
import main.initialization.InitializeValues;
import main.inventory.RentalTool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class RentalTotals {

    @Getter RentalTool rentalTool;
    @Getter int rentalDayCount;
    @Getter int discountPercent;
    @Getter LocalDate checkoutDate;
    @Getter int chargedDays;
    @Getter BigDecimal preDiscountChargedAmount;
    @Getter BigDecimal discountAmount;
    @Getter BigDecimal finalChargedAmount;
    @Getter String errorMessage;

    public RentalTotals calculateRentalValues(InitializeValues initializeValues, RentalTool rentalTool, int rentalDayCount,
                                              int discountPercent, LocalDate checkoutDate) throws Exception {

        this.rentalTool = rentalTool;
        this.rentalDayCount = rentalDayCount;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;

        try {
            TransactionRentTool.isValidDate(checkoutDate);
            TransactionRentTool.validateDiscountPercentage(discountPercent);
            TransactionRentTool.validateRentalDayCount(rentalDayCount);
            TransactionRentTool.validateRentalTool(initializeValues.RentalToolInventory, rentalTool.getToolCode());

            this.chargedDays = CalendarCalculations.calculateRentalChargedDays(rentalTool, checkoutDate, rentalDayCount);
            this.preDiscountChargedAmount = rentalTool.getRentalPrice().multiply(BigDecimal.valueOf(chargedDays));
            this.discountAmount = preDiscountChargedAmount.multiply(BigDecimal.valueOf(discountPercent * .01))
                    .setScale(2, RoundingMode.HALF_UP);
            this.finalChargedAmount = preDiscountChargedAmount.subtract(discountAmount);
        } catch(Exception ex) {
            errorMessage = ex.toString();
        }
        return this;
    }

}
