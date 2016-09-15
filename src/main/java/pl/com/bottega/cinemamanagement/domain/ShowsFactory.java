package pl.com.bottega.cinemamanagement.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Dell on 2016-09-08.
 */
public class ShowsFactory {

    public List<Show> createShows(Cinema cinema, Movie movie, List<LocalDateTime> dates) {
        checkArgument(!(cinema == null || movie == null || dates == null || dates.isEmpty()));

        List<Show> shows = new LinkedList<>();
        for (LocalDateTime date : dates)
            shows.add(new Show(cinema, date.toLocalDate(), movie, date.toLocalTime()));
        return shows;
    }

    public List<Show> createShows(Cinema cinema, Movie movie, Calendar calendar) {
        List<Show> shows = new LinkedList<>();
        List<LocalDateTime> dates = addDate(calendar);
        for (LocalDateTime date : dates)
            shows.add(new Show(cinema, date.toLocalDate(), movie, date.toLocalTime()));
        return shows;
    }

    private List<LocalDateTime> addDate(Calendar calendar) {
        LocalDateTime fromDate = calendar.getFromDate();
        LocalDateTime untilDate = calendar.getUntilDate();
        List<LocalDateTime> totalDates = new ArrayList<>();
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
