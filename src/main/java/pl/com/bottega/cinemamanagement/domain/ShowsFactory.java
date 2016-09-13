package pl.com.bottega.cinemamanagement.domain;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Dell on 2016-09-08.
 */
public class ShowsFactory {

    public List<Show> createShows(Cinema cinema, Movie movie, List<LocalDateTime> dates) {
        List<Show> shows = new LinkedList<>();
        for (LocalDateTime date : dates)
            shows.add(new Show(cinema, date.toLocalDate(), movie, date.toLocalTime()));
        return shows;
    }

    public List<Show> createShows(Cinema cinema, Movie movie, Calendar calendar) {

        return null;
    }
}
