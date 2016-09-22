package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.domain.*;

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
        List<Show> shows = prepare(cinema, movie, request);
        for (Show show : shows)
            showsRepository.save(show);
    }

    @Transactional
    public void updatePrices(Long movieId, UpdatePriceRequest updatePriceRequest) {
        Movie movie = movieRepository.findById(movieId);
        if (movie == null)
            throw new  InvalidRequestException("Wrong id. Movie does not exist.");
        Set<TicketPrice> ticketPrices = changeMapToSet(updatePriceRequest.getPrices());
        movie.updatePrices(ticketPrices);
    }

    private Set<TicketPrice> changeMapToSet(HashMap<String, BigDecimal> prices) {
        Set<TicketPrice> ticprice = new HashSet<>();
        for (Map.Entry<String, BigDecimal> entry : prices.entrySet())
            ticprice.add(new TicketPrice(entry.getKey().toLowerCase(), entry.getValue()));
        return ticprice;
    }

    private List<Show> prepare(Cinema cinema, Movie movie, CreateShowRequest request) {
        if (request.getDates() != null)
            return new ShowsFactory().createShows(cinema, movie, request.getDates());
        else
            return new ShowPreparationWithCalendar().prepare(cinema, movie, request.getCalendarDto());
    }
}
