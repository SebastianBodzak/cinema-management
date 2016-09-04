package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.CinemaRepository;

/**
 * Created by Dell on 2016-09-04.
 */
public class CreateCinemaRequest {

    private String name;
    private String city;

    public void validate(CinemaRepository repository) {
        if (name == null) {
            throw new InvalidRequestException("value NAME can not be empty");
        }

        if (city == null) {
            throw new InvalidRequestException("value CITY can not be empty");
        }

    }

}




