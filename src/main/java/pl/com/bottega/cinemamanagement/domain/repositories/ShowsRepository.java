package pl.com.bottega.cinemamanagement.domain.repositories;

import pl.com.bottega.cinemamanagement.domain.Show;

/**
 * Created by Dell on 2016-09-08.
 */
public interface ShowsRepository {

    void save(Show show);

    Show findById(Long id);


    Show showWithTicketPrices(Long showId);

    Show findShowWithReservations(Long showId);
}
