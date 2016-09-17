package pl.com.bottega.cinemamanagement.api;

import java.time.Year;

/**
 * Created by Dell on 2016-09-15.
 */
public class Utils {

    /**
     * It is working with date format which is starting with "yyyy/MM/dd"
     * @param date
     */
    public static void checkAdditionalDates(String date) {
        if (date.length() >= 10)
            date = date.substring(0, 10);
        int year = Integer.valueOf(date.substring(0, 4));
        int month = Integer.valueOf(date.substring(5, 7));
        int day = Integer.valueOf(date.substring(8));
        if (checkIfDatesAreNotCorrect(year, month, day))
            throw new IllegalArgumentException();
    }

    private static boolean checkIfDatesAreNotCorrect(int year, int month, int day) {
        return (month == 2 && checkIfFebruaryDateIsInvalid(year, day)) || (day == 31 && (month == 4 || month == 6 || month == 9 || month == 11));
    }

    private static boolean checkIfFebruaryDateIsInvalid(int year, int day) {
        return (day == 30 || day == 31) || (!checkIfIsLeapYear(year) && day == 29);
    }

    private static boolean checkIfIsLeapYear(int year) {

        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
      //  ((year & 3) == 0 && ((year % 25) != 0 || (year & 15) == 0))  //TODO
    }
}
