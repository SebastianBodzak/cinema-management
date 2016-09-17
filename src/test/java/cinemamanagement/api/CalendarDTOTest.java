package cinemamanagement.api;

import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.cinemamanagement.api.CalendarDto;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;

import java.util.ArrayList;
import java.util.Collection;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Bartosz on 2016-09-09.
 */
public class CalendarDTOTest {

    private CalendarDto calendarDto;
    private String fromDate = "2016/09/13 01:00";
    private String untilDate = "2016/09/30 23:00";
    private Collection<String> weekDays = new ArrayList<String>() {{
        add("Monday");
        add("Tuesday");
        add("Wednesday");
    }};
    private Collection<String> weekDaysWithNull = new ArrayList<String>() {{
        add(null);
        add("Tuesday");
        add("Wednesday");
    }};
    private Collection<String> weekDaysWithEmptyElement = new ArrayList<String>() {{
        add("Monday");
        add("");
        add("Wednesday");
    }};
    private Collection<String> weekDaysWithNameError = new ArrayList<String>() {{
        add("Mnday");
        add("Tuesday");
        add("Wednesday");
    }};
    private Collection<String> hours = new ArrayList<String>() {{
        add("13:00");
        add("15:00");
        add("17:00");
    }};
    private Collection<String> hoursWithNull = new ArrayList<String>() {{
        add(null);
        add("15:00");
        add("17:00");
    }};
    private Collection<String> hoursWithEmptyElement = new ArrayList<String>() {{
        add("13:00");
        add("");
        add("17:00");
    }};
    private Collection<String> hoursWithErrorHour = new ArrayList<String>() {{
        add("13:00");
        add("28:00");
        add("17:00");
    }};


    @Before
    public void Setup() {
        calendarDto = new CalendarDto();

    }

    @Test
    public void shouldValidate(){
        setCalendarDtoInstance(fromDate, untilDate, weekDays, hours);

        calendarDto.validate();
    }

    @Test
    public void shouldNotValidateWithWrongDate(){
        setCalendarDtoInstance(fromDate, "2016/09/35 23:00", weekDays, hours);

        try {
            calendarDto.validate();
        } catch (InvalidRequestException ex) {
            assertEquals("Invalid date format", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldNotValidateWithLetterInDate(){
        setCalendarDtoInstance(fromDate, "2016/09/2t 23:00", weekDays, hours);

        try {
            calendarDto.validate();
        } catch (InvalidRequestException ex) {
            assertEquals("Invalid date format", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldNotValidateWithNullDays(){
        setCalendarDtoInstance(fromDate, untilDate, weekDaysWithNull, hours);

        try {
            calendarDto.validate();
        } catch (InvalidRequestException ex) {
            assertEquals("Value of day/hour can not be null", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");

    }

    @Test
    public void shouldNotValidateEmptyDays(){
        setCalendarDtoInstance(fromDate, untilDate, weekDaysWithEmptyElement, hours);

        try {
            calendarDto.validate();
        } catch (InvalidRequestException ex) {
            assertEquals("Value weekDays can not be empty", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldNotValidateErrorNameDays(){
        setCalendarDtoInstance(fromDate, untilDate, weekDaysWithNameError, hours);

        try {
            calendarDto.validate();
        } catch (InvalidRequestException ex) {
            assertEquals("Value weekDays can not be empty", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");
    }

    @Test
    public void shouldNotValidateWithNullHours(){
        setCalendarDtoInstance(fromDate, untilDate, weekDays, hoursWithNull);

        try {
            calendarDto.validate();
        } catch (InvalidRequestException ex) {
            assertEquals("Value of day/hour can not be null", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");

    }
    @Test
    public void shouldNotValidateWithEmptyHours(){
        setCalendarDtoInstance(fromDate, untilDate, weekDays, hoursWithEmptyElement);

        try {
            calendarDto.validate();
        } catch (InvalidRequestException ex) {
            assertEquals("Value hours can not be empty", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");

    }

    @Test
    public void shouldNotValidateWithErrorHours(){
        setCalendarDtoInstance(fromDate, untilDate, weekDays, hoursWithErrorHour);

        try {
            calendarDto.validate();
        } catch (InvalidRequestException ex) {
            assertEquals("Invalid value of hour", ex.getMessage());
            return;
        }
        fail("InvalidRequestException expected");

    }


    private void setCalendarDtoInstance(String fromDate, String untilDate, Collection<String> weekDays, Collection<String> hours) {
        calendarDto.setFromDate(fromDate);
        calendarDto.setUntilDate(untilDate);
        calendarDto.setWeekDays(weekDays);
        calendarDto.setHours(hours);
    }

    @Test
    public void shouldNotAllowDateFromToBeGreaterThanUntil(){

            setCalendarDtoInstance(fromDate, "2016/09/10 23:00", weekDays, hours);

            try {
                calendarDto.validate();
            } catch (InvalidRequestException ex) {
                assertEquals("Date From cant be greater than date until", ex.getMessage());
                return;
            }
            fail("InvalidRequestException expected");
        }
}
