package cinemamanagement.api;

import org.junit.Test;
import pl.com.bottega.cinemamanagement.api.CalendarDto;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Bartosz on 2016-09-09.
 */
public class CalendarDTOTest {


    @Test
    public void shouldValidate(){
        Collection<String> days;
        Collection<String> hours;
        String[] d = {"monday","wednesday"};
        String[] h = {"13:00","15:00"};
        days = Arrays.asList(d);
        hours = Arrays.asList(h);
        CalendarDto cd = new CalendarDto();
        cd.setFromDate("2016/01/01 11:20");
        cd.setUntilDate("2016/02/01 11:20");
        cd.setWeekDays(days);
        cd.setHours(hours);

        cd.validate();
    }
}
