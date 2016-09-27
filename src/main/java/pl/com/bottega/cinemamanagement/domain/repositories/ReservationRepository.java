package pl.com.bottega.cinemamanagement.domain.repositories;

import pl.com.bottega.cinemamanagement.api.ReservationCriteria;
import pl.com.bottega.cinemamanagement.api.ReservationSearchResult;
import pl.com.bottega.cinemamanagement.domain.Reservation;

/**
 * Created by ulvar on 25.09.2016.
 */
public interface ReservationRepository {
    void save(Reservation reservation);

    ReservationSearchResult find(ReservationCriteria criteria);
}
