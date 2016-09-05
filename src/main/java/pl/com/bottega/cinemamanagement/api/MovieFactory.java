package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.Movie;

/**
 * Created by arkadiuszarak on 05/09/2016.
 */
public class MovieFactory {
    public static Movie createMovie(CreateMovieRequest request) {
        return new Movie(request.getMovie().getTitle(),
                         request.getMovie().getDescription(),
                         request.getMovie().getActors(),
                         request.getMovie().getGeners(),
                         request.getMovie().getMinAge(),
                         request.getMovie().getLenght());
    }
}
