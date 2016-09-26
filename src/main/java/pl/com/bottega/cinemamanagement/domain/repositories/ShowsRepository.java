package pl.com.bottega.cinemamanagement.domain.repositories;

import pl.com.bottega.cinemamanagement.domain.Show;
import pl.com.bottega.cinemamanagement.domain.TicketPrice;

import java.util.Set;

/**
 * Created by Dell on 2016-09-08.
 */
public interface ShowsRepository {

    void save(Show show);

    Show findById(Long id);


    Set<TicketPrice> listTicketPrices(Long showId);

    Show findShowWithReservations(Long showId);
}