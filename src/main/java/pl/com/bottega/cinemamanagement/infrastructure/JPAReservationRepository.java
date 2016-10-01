package pl.com.bottega.cinemamanagement.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemamanagement.api.ReservationCriteria;
import pl.com.bottega.cinemamanagement.domain.Reservation;
import pl.com.bottega.cinemamanagement.domain.repositories.ReservationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    public List<Reservation> findActualReservations(ReservationCriteria criteria) {
        return entityManager.createNamedQuery("Reservation.findReservation", Reservation.class).
                setParameter("lastName", criteria.getLastName()).setParameter("status", criteria.getStatus()).
                setParameter("date", LocalDate.now()).setParameter("time", LocalTime.now().minusHours(1)).getResultList();
    }

    private boolean reservationHasAvailableShow(Reservation reservation) {
        return reservation.getShowDate() == LocalDate.now() && reservation.getShowTime().isBefore(LocalTime.now().minusHours(1));
    }
}
