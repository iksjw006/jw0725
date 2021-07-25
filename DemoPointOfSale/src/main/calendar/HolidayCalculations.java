package main.calendar;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;

public class HolidayCalculations {

    public static HashMap<String, String> getAllHolidaysForSpecifiedYear(int year){
        HashMap<String, String> holidayDates = new HashMap();
        holidayDates.putAll(HolidayCalculations.getDynamicHolidayListForSpecifiedYear(year));
        holidayDates.putAll(HolidayCalculations.getStaticHolidayListForSpecifiedYear(year));
        return holidayDates;
    }

    public static HashMap<String, String> getDynamicHolidayListForSpecifiedYear(int year) {
        HashMap<String, String> HolidayList = new HashMap();
        // Labor Day - first Mon in Sept
        HolidayList.put(
                GetNthDayOfNthWeek(LocalDate.of(year, 9, 1), DayOfWeek.MONDAY, 1),
                "Labor Day"
        );
        return HolidayList;
    }

    public static HashMap<String, String> getStaticHolidayListForSpecifiedYear(int year) {

        HashMap<String,String> holidayList = new HashMap();
        // Independence Day - static date - July 4
        holidayList.put(calculateHolidayOffset(LocalDate.of(year, 7, 4)), "Independence Day");
        return holidayList;
    }

    private static String calculateHolidayOffset(LocalDate holiday){
        // Saturday Holidays are moved to Friday and Sunday Holidays are moved to Monday
        if (holiday.getDayOfWeek().name().toUpperCase().equals(DayOfWeek.SATURDAY.toString().toUpperCase())){
            holiday = holiday.minusDays(1);
        }
        if (holiday.getDayOfWeek().name().toUpperCase().equals(DayOfWeek.SUNDAY.toString().toUpperCase())) {
            holiday = holiday.plusDays(1);
        }
        return holiday.toString();
    }

    public static String GetNthDayOfNthWeek(LocalDate localDate, DayOfWeek dayOfWeek, int weekOfHoliday) {

        LocalDate firstDayOfMonth = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
        LocalDate firstDayOfWeek = firstDayOfMonth.with(firstInMonth(dayOfWeek));
        // Date for specified week
        firstDayOfWeek = firstDayOfWeek.plusDays((weekOfHoliday - 1) * 7);

        // If day is after end of month adjust back a week
        if (firstDayOfWeek.isAfter(firstDayOfMonth.plusMonths(1).minusDays(1))) {
            firstDayOfWeek = firstDayOfWeek.minusDays(7);
        }
        return firstDayOfWeek.toString();
    }

}
