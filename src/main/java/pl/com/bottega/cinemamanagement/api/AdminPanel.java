package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.domain.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dell on 2016-09-04.
 */
@Service
public class AdminPanel {

    private CinemaRepository cinemaRepository;
    private MovieRepository movieRepository;
    private CinemaFactory cinemaFactory;
    private MovieFactory movieFactory;
    private ShowsRepository showsRepository;

    public AdminPanel(CinemaRepository cinemaRepository, MovieRepository movieRepository, CinemaFactory cinemaFactory, MovieFactory movieFactory, ShowsRepository showsRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.cinemaFactory = cinemaFactory;
        this.movieFactory = movieFactory;
        this.showsRepository = showsRepository;
    }

    @Transactional
    public void createCinema(CreateCinemaRequest request) {
        request.validate();
        Cinema cinema = cinemaRepository.load(request.getName(), request.getCity());
        if (cinema == null) {
            cinema = cinemaFactory.create(request.getName(), request.getCity());
            cinemaRepository.save(cinema);
        } else
            throw new InvalidRequestException("Cinema already exists");
    }

    @Transactional
    public void createMovie(CreateMovieRequest request) {
        request.validate();
        Movie movie = movieFactory.create(request.getMovie().getTitle(),
                request.getMovie().getDescription(),
                request.getMovie().getActors(),
                request.getMovie().getGenres(),
                request.getMovie().getMinAge(),
                request.getMovie().getLength());
        movieRepository.save(movie);
    }

    @Transactional
    public void createShows(CreateShowRequest request) {
        request.validate();
        Cinema cinema = cinemaRepository.findById(request.getCinemaId());
        Movie movie = movieRepository.findById(request.getMovieId());
        if (cinema == null || movie == null)
            throw new InvalidRequestException("Cinema or Movie does not exist");
        List<Show> shows = prepare(cinema, movie, request);
        for (Show show : shows)
            showsRepository.save(show);
    }

    private List<Show> prepare(Cinema cinema, Movie movie, CreateShowRequest request) {
        if (request.getDates() != null)
            return prepareShowsWithDates(cinema, movie, request.getDates());
        else
            return new ShowPreparationWithCalendar().prepare(cinema, movie, request.getCalendarDto());
    }

    private List<Show> prepareShowsWithDates(Cinema cinema, Movie movie, Collection<String> dates) {
        List<LocalDateTime> datesList = parseStringsToDates(dates);
        return new ShowsFactory().createShows(cinema, movie, datesList);
    }

    private List<LocalDateTime> parseStringsToDates(Collection<String> stringDates) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        List<LocalDateTime> dates = new LinkedList<>();
        for (String s : stringDates)
            dates.add(LocalDateTime.parse(s, formatter));
        return dates;
    }
}
