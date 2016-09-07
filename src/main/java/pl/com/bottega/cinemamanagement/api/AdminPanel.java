package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.CinemaRepository;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.MovieRepository;

/**
 * Created by Dell on 2016-09-04.
 */
@Service
public class AdminPanel {

    private CinemaRepository cinemaRepository;
    private MovieRepository movieRepository;
    private CinemaFactory cinemaFactory;
    private MovieFactory movieFactory;

    public AdminPanel(CinemaRepository cinemaRepository, MovieRepository movieRepository, CinemaFactory cinemaFactory, MovieFactory movieFactory) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.cinemaFactory = cinemaFactory;
        this.movieFactory = movieFactory;
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
        Movie movie = MovieFactory.create(request.getMovie().getTitle(),
                request.getMovie().getDescription(),
                request.getMovie().getActors(),
                request.getMovie().getGenres(),
                request.getMovie().getMinAge(),
                request.getMovie().getLength());
        movieRepository.save(movie);

    }

    public void createShow(CreateShowRequest request) {
        //todo
    }


}
