package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.domain.*;

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
    public void createCinema(CreateCinemaRequest request) throws InvalidRequestException {
        request.validate(cinemaRepository);
        Cinema cinema = cinemaRepository.load(request.getName(), request.getCity());
        if (cinema == null) {
            cinema = cinemaFactory.create(request.getName(), request.getCity());
            cinemaRepository.save(cinema);
        }
        else
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
    public void createShow(Long cinemaId, CreateShowRequest request) {
        Cinema cinema = cinemaRepository.findById(cinemaId);
        request.validate();
        Movie movie = movieRepository.findById(request.getMovieId());
        Show show = chooseShowVersion(cinema, movie, request);
        showsRepository.save(show);
    }

    private Show chooseShowVersion(Cinema cinema, Movie movie, CreateShowRequest request) {
        ShowsFactory showsFactory = new ShowsFactory();
//        if (request.getParseDates() != null)
            return showsFactory.createShows(cinema, movie, request.getParseDates());
//        else (request.getCalendar() != null)
//            return showsFactory.createShows(cinema, movie, request.getCalendar());
    }


}
