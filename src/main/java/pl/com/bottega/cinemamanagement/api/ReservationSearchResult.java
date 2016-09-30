package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.api.dtos.CustomerReservationsDto;

/**
 * Created by Bartosz on 2016-09-27.
 */
public class ReservationSearchResult {

    private CustomerReservationsDto result;

    public ReservationSearchResult(CustomerReservationsDto result) {
        this.result = result;
    }

    public CustomerReservationsDto getResult() {
        return result;
    }
}
