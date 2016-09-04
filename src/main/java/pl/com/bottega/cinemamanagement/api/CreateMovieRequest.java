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
        //todo
    }
}
