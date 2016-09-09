package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.domain.*;

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
    public void createShows(Long cinemaId, CreateShowRequest request) throws InvalidRequestException {
        request.validate();
        Cinema cinema = cinemaRepository.findById(cinemaId);
        Movie movie = movieRepository.findById(request.getMovieId());
        List<Show> shows = request.prepareShows(cinema, movie);
        for (Show show : shows)
            showsRepository.save(show);
    }

}
