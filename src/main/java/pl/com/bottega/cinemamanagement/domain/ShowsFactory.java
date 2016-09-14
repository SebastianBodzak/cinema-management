package pl.com.bottega.cinemamanagement.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Dell on 2016-09-08.
 */
public class ShowsFactory {

    public List<Show> createShows(Cinema cinema, Movie movie, List<LocalDateTime> dates) {
        checkArgument(!(cinema == null || movie == null || dates == null));

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

    //TODO

    //    private void addDate(Calendar calendar) {
//        LocalDate fromDate = calendar.getFromDate(); //13.09 (pon, wt)
//        LocalDate untilDate = calendar.getUntilDate(); //28.09
//        List<LocalDate> totalDates = new ArrayList<>();
//        while (!fromDate.isAfter(untilDate)) { //ustala zasięg dat i stopniowo go zmniejsza
//            if (calendar.getWeekDays().contains(fromDate.getDayOfWeek())) { //porównuje dni tygodnia
//                for (LocalTime hours : calendar.getHours())
//                    totalDates.add(addHours(fromDate, hours));
//            }
//            fromDate = fromDate.plusDays(1);
//        }
//    }
//
    private LocalDate addHours(LocalDate date, LocalTime hours) {
        return date.with(hours);
    }

    private List<LocalDateTime> addDate(Calendar calendar) {
        LocalDateTime fromDate = calendar.getFromDate(); //13.09 (pon, wt)
        LocalDateTime untilDate = calendar.getUntilDate(); //28.09
        List<LocalDateTime> totalDates = new ArrayList<>();
        while (!fromDate.toLocalDate().isAfter(untilDate.toLocalDate())) { //ustala zasięg dat i stopniowo go zmniejsza
            if (calendar.getWeekDays().contains(fromDate.getDayOfWeek())) { //porównuje dni tygodnia
                for (LocalTime hours : calendar.getHours()) {
                    totalDates.add(LocalDateTime.of(fromDate.toLocalDate(), hours));
                }
            }
            fromDate = fromDate.plusDays(1);
        }
        return totalDates;
    }
}
