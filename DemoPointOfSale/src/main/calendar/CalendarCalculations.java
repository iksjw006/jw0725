package main.calendar;

import main.inventory.RentalTool;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CalendarCalculations {

    public static int calculateRentalChargedDays(RentalTool rentalTools, LocalDate checkoutDate, int rentalDayCount) {
        int finalChargeDateCount = rentalDayCount;
        LocalDate comparingDate = checkoutDate.plusDays(1);
        int compareYear = checkoutDate.getYear();
        Map<String, String> holidayDate = getHolidaysForSpecifiedYear(compareYear);

        while (comparingDate.isBefore(checkoutDate.plusDays(rentalDayCount))) {
            if((!rentalTools.isChargeHoliday() && holidayDate.containsKey(comparingDate.toString())) ||
                    (!rentalTools.isChargeWeekend() && isWeekendDate(comparingDate)) ||
                    (!rentalTools.isChargeWeekday() && isWeekdayDate(comparingDate))){
                finalChargeDateCount--;
            }
            comparingDate = comparingDate.plusDays(1);

            // Checking dynamic and static holidays, confirm that the year has not been incremented.
            if (compareYear != comparingDate.getYear()) {
                holidayDate = getHolidaysForSpecifiedYear(comparingDate.getYear());
            }
        }
        return finalChargeDateCount;
    }

    private static HashMap<String, String> getHolidaysForSpecifiedYear(int year){
        return HolidayCalculations.getAllHolidaysForSpecifiedYear(year);
    }

    private static boolean isWeekendDate(LocalDate rentalDate){
        return rentalDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                || rentalDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    private static boolean isWeekdayDate(LocalDate rentalDate){
        return !(rentalDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                || rentalDate.getDayOfWeek().equals(DayOfWeek.SUNDAY));
    }


}
