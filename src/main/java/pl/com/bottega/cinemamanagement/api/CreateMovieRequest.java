package pl.com.bottega.cinemamanagement.api;

import java.util.Collection;

/**
 * Created by Dell on 2016-09-04.
 */
public class CreateMovieRequest {

    private String title;
    private String description;
    private Collection<String> actors;

    public void validate() {
        if (title == null) {
            throw new InvalidRequestException("value TITLE can not be empty");
        }

        if (description == null) {
            throw new InvalidRequestException("value DESCRIPTION can not be empty");
        }

        if (actors == null) {
            throw new InvalidRequestException("value ACTORS can not be empty");
        }

    }
}
