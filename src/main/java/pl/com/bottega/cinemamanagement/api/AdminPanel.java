package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.domain.*;
import pl.com.bottega.cinemamanagement.domain.Calendar;

import java.math.BigDecimal;
import java.util.*;

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
    private ShowsFactory showsFactory;

    public AdminPanel(CinemaRepository cinemaRepository, MovieRepository movieRepository, CinemaFactory cinemaFactory,
                      MovieFactory movieFactory, ShowsRepository showsRepository, ShowsFactory showsFactory) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.cinemaFactory = cinemaFactory;
        this.movieFactory = movieFactory;
        this.showsRepository = showsRepository;
        this.showsFactory = showsFactory;
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
        Movie movie = movieFactory.create(request);
        movieRepository.save(movie);
    }

    @Transactional
    public void createShows(CreateShowRequest request) {
        request.validate();
        Cinema cinema = cinemaRepository.findById(request.getCinemaId());
        Movie movie = movieRepository.findById(request.getMovieId());
        if (cinema == null || movie == null)
            throw new InvalidRequestException("Cinema or Movie does not exist");
        Calendar calendar = prepare(cinema, movie, request.getCalendarDto());
        List<Show> shows = showsFactory.createShows(cinema, movie, request.getDates(), calendar);
        for (Show show : shows)
            showsRepository.save(show);
    }

    @Transactional
    public void updatePrices(UpdatePriceRequest updatePriceRequest) {
        Movie movie = movieRepository.findById(updatePriceRequest.getMovieId());
        if (movie == null)
            throw new  InvalidRequestException("Wrong id. Movie does not exist.");
        updatePriceRequest.validate();
        Set<TicketPrice> ticketPrices = changeMapToSet(updatePriceRequest.getPrices());
        movie.updatePrices(ticketPrices);
    }

    private Set<TicketPrice> changeMapToSet(HashMap<String, BigDecimal> prices) {
        Set<TicketPrice> ticprice = new HashSet<>();
        for (Map.Entry<String, BigDecimal> entry : prices.entrySet())
            ticprice.add(new TicketPrice(entry.getKey().toLowerCase(), entry.getValue()));
        return ticprice;
    }

    private Calendar prepare(Cinema cinema, Movie movie, CalendarDto calendarDto) {
        if (calendarDto != null)
            return new ShowPreparationWithCalendar().prepare(cinema, movie, calendarDto);
        return null;
    }
}
