package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.CinemaRepository;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.MovieRepository;

import java.util.List;

/**
 * Created by Dell on 2016-09-04.
 */
@Service
public class AdminPanel {

    private CinemaRepository cinemaRepository;
    private MovieRepository movieRepository;

    public AdminPanel(CinemaRepository cinemaRepository, MovieRepository movieRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    public void createCinema(CreateCinemaRequest request) {
        Cinema cinema = cinemaRepository.load(request.getName(), request.getCity());
        if (cinema == null) {
            cinema = new Cinema(request.getName(), request.getCity());
            cinemaRepository.save(cinema);
        }
        else
            throw new InvalidRequestException("Cinema already exists");
    }

    @Transactional
    public void createMovie(CreateMovieRequest request) {
        Movie movie = MovieFactory.createMovie(request);
        if (movie == null)

        movieRepository.save(movie);

    }

    public void createShow(CreateShowRequest request) {
        //todo
    }


}
