package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.Show;

import java.time.LocalTime;
import java.util.*;

/**
 * Created by arkadiuszarak on 18/09/2016.
 */
public class ListMoviesInCinemaResponse {

    private List<MovieDto> movies = new LinkedList<>();

    public ListMoviesInCinemaResponse(Collection<Movie> movies) {
        for (Movie movie : movies){
            this.movies.add(new MovieDto(movie));
        }
    }

    public Collection<MovieDto> getMovies() {
        return movies;
    }

    public class MovieDto {
        private String title;
        private String description;
        private Collection<String> actors;
        private Collection<String> generes;
        private Integer minAge;
        private Integer length;
        private List<ShowDto> shows;


        public Collection<String> getActors() {
            return actors;
        }

        public String getDescription() {
            return description;
        }

        public Collection<String> getGeneres() {
            return generes;
        }

        public Integer getLength() {
            return length;
        }

        public Integer getMinAge() {
            return minAge;
        }

        public Collection<ShowDto> getShows() {
            return shows;
        }

        public String getTitle() {
            return title;
        }

        public MovieDto(Movie movie) {
            this.title = movie.getTitle();
            this.description = movie.getDescription();
            this.actors = movie.getActors();
            this.generes = movie.getGenres();
            this.minAge = movie.getMinAge();
            this.length = movie.getLength();
            this.shows = new ArrayList<>();
            for (Show show : movie.getShows()){
                this.shows.add(new ShowDto(show));
            }
        }
    }

    public class ShowDto {
        private Long id;
        private LocalTime time;

        public ShowDto(Show show) {
            this.id = show.getId();
            this.time = show.getTime();
        }

        public Long getId() {
            return id;
        }

        public LocalTime getTime() {
            return time;
        }
    }
}
