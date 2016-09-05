package pl.com.bottega.cinemamanagement.api;

import java.util.Collection;

/**
 * Created by Dell on 2016-09-04.
 */

public class CreateMovieRequest {

    private MovieDto movie;

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    public class MovieDto {

        private String title;
        private String description;
        private Collection<String> actors;
        private Collection<String> genres;
        private int minAge;
        private int length;

        public Collection<String> getActors() {
            return actors;
        }

        public String getDescription() {
            return description;
        }

        public String getTitle() {
            return title;
        }

        public void setActors(Collection<String> actors) {
            this.actors = actors;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Collection<String> getGeners() {
            return genres;
        }

        public int getLenght() {
            return length;
        }

        public int getMinAge() {
            return minAge;
        }

        public void setGeners(Collection<String> geners) {
            this.genres = geners;
        }

        public void setLenght(int lenght) {
            this.length = lenght;
        }

        public void setMinAge(int minAge) {
            this.minAge = minAge;
        }

        public void validate() {
            if (title == null || title.trim().isEmpty())
                throw new InvalidRequestException("value TITLE can not be empty");

            if (description == null || description.trim().isEmpty())
                throw new InvalidRequestException("value DESCRIPTION can not be empty");

            if (actors == null)
                //TODO check if actors on the list are not null
                throw new InvalidRequestException("value ACTORS can not be empty");

            if (genres == null)
                //TODO check if genres on the list are not null
                throw new InvalidRequestException("value GENRES can not be empty");

            if (minAge == 0)
                throw new InvalidRequestException("value MIN AGE can not be empty");

            if (length == 0)
                throw new InvalidRequestException("value LENGTH can not be empty");
        }
    }
        public void validate() {
            if (movie == null) {
                throw new InvalidRequestException("Movie not specified");
            }
            movie.validate();
        }
    }

