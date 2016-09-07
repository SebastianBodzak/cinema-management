package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.CinemaRepository;

/**
 * Created by Dell on 2016-09-04.
 */
public class CreateCinemaRequest {

    private CinemaDto cinema;



    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
        this.cinema = cinema;
    }

    public void validate(CinemaRepository repository) throws InvalidRequestException {
        if (cinema == null)
            throw new InvalidRequestException("Cinema is required");
        cinema.validate();
    }

    public String getName() {
        return cinema.getName();
    }

    public String getCity() {
        return cinema.getCity();
    }
}




