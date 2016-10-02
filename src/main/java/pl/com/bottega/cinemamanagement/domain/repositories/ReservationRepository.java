package pl.com.bottega.cinemamanagement.domain.repositories;

import pl.com.bottega.cinemamanagement.api.ReservationCriteria;
import pl.com.bottega.cinemamanagement.domain.Reservation;

import java.util.List;

/**
 * Created by ulvar on 25.09.2016.
 */
public interface ReservationRepository {
    void save(Reservation reservation);

    List<Reservation> findActualReservations(ReservationCriteria criteria);

    Reservation findReservationByNumber(Long reservationNumber);
}
