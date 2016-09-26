package pl.com.bottega.cinemamanagement.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemamanagement.domain.Show;
import pl.com.bottega.cinemamanagement.domain.repositories.ShowsRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Created by Dell on 2016-09-08.
 */
@Repository
public class JPAShowsRepository implements ShowsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Show show) {
        entityManager.persist(show);
    }

    @Override
    public Show findById(Long id) {
        return entityManager.find(Show.class, id);
    }

    @Override
    public Show showWithTicketPrices(Long showId) {
        String jpa = "SELECT DISTINCT sh FROM Show sh " +
                "JOIN FETCH sh.movie m " +
                "JOIN FETCH m.ticketPrices tp " +
                "WHERE sh.id = :showId";

        TypedQuery<Show> query = entityManager.createQuery(jpa, Show.class);
        query.setParameter("showId", showId);
        return query.getResultList().get(0);
    }

    @Override
    public Show findShowWithReservations(Long showId) {
        String jpa = "SELECT DISTINCT sh FROM Show sh " +
                "JOIN FETCH sh.movie m " +
                "JOIN FETCH m.ticketPrices tp " +
                "LEFT JOIN FETCH sh.reservations r " +
                "WHERE sh.id = :showId";
        Query query = entityManager.createQuery(jpa).setParameter("showId", showId);
        return (Show) query.getResultList().get(0);
    }
}
