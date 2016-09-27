package pl.com.bottega.cinemamanagement.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemamanagement.api.ReservationCriteria;
import pl.com.bottega.cinemamanagement.api.ReservationSearchResult;
import pl.com.bottega.cinemamanagement.domain.Reservation;
import pl.com.bottega.cinemamanagement.domain.repositories.ReservationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Bartosz on 2016-09-27.
 */
@Repository
public class JPAReservationRepository implements ReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public ReservationSearchResult find(ReservationCriteria criteria) {
        return null;
    }
}
