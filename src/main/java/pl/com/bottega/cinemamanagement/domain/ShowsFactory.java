package pl.com.bottega.cinemamanagement.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static com.google.common.base.Preconditions.*;

/**
 * Created by Dell on 2016-09-08.
 */
@Component
public class ShowsFactory {

    public List<Show> createShows(Cinema cinema, Movie movie, Collection<LocalDateTime> dates, Calendar calendar) {
        checkArgument(checkIfArgumentsAreValidate(cinema, movie, dates, calendar));

        List<Show> shows = new LinkedList<>();
        if (calendar != null)
            dates = createDates(calendar);
        for (LocalDateTime date : dates)
            shows.add(new Show(cinema, date.toLocalDate(), movie, date.toLocalTime()));
        return shows;
    }

    private boolean checkIfArgumentsAreValidate(Cinema cinema, Movie movie, Collection<LocalDateTime> dates, Calendar calendar) {
        return !(cinema == null || movie == null || checkIfIsParameter(dates, calendar) || checkIfOnlyOneParameter(dates, calendar));
    }

    private boolean checkIfIsParameter(Collection<LocalDateTime> dates, Calendar calendar) {
        return (dates == null || dates.isEmpty()) && calendar == null;
    }

    private boolean checkIfOnlyOneParameter(Collection<LocalDateTime> dates, Calendar calendar) {
        return dates != null && calendar != null;
    }

    private List<LocalDateTime> createDates(Calendar calendar) {
        LocalDateTime fromDate = calendar.getFromDate();
        LocalDateTime untilDate = calendar.getUntilDate();
        List<LocalDateTime> totalDates = new LinkedList<>();
        while (!fromDate.toLocalDate().isAfter(untilDate.toLocalDate())) {
            if (calendar.getWeekDays().contains(fromDate.getDayOfWeek())) {
                for (LocalTime hours : calendar.getHours()) {
                    totalDates.add(LocalDateTime.of(fromDate.toLocalDate(), hours));
                }
            }
            fromDate = fromDate.plusDays(1);
        }
        return totalDates;
    }
}
