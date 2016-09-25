package pl.com.bottega.cinemamanagement.domain;

import java.util.UUID;

/**
 * Created by ulvar on 25.09.2016.
 */
public class ReservationNumber {
    private String number;

    public ReservationNumber() {
        this.number = UUID.randomUUID().toString();
    }
}
