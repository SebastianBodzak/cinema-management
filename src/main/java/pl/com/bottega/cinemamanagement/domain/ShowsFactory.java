package pl.com.bottega.cinemamanagement.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dell on 2016-09-08.
 */
public class ShowsFactory {

    public List<Show> createShows(Cinema cinema, Movie movie, List<Date> dates) {
        List<Show> shows = new LinkedList<>();
        for (Date date : dates)
            shows.add(new Show(cinema, date, movie));
        return shows;
    }

    public List<Show> createShows(Cinema cinema, Movie movie, Calendar calendar) {
        return null;
    }
}
