package pl.com.bottega.cinemamanagement.api.responses;

import pl.com.bottega.cinemamanagement.api.dtos.SeatsDto;

import java.util.Set;

/**
 * Created by arkadiuszarak on 28/09/2016.
 */
public class ListSeatsResponse {
    private SeatsDto seats;

    public ListSeatsResponse(SeatsDto seats) {
        this.seats = seats;
    }

    public SeatsDto getSeats() {
        return seats;
    }

    public void setSeats(SeatsDto seats) {
        this.seats = seats;
    }

}
