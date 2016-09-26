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
        TypedQuery<Show> query = entityManager.createNamedQuery("Show.showWithTicketPrices", Show.class);
        query.setParameter("showId", showId);
        return query.getResultList().get(0);
    }

    @Override
    public Show findShowWithReservations(Long showId) {
        TypedQuery<Show> query = entityManager.createNamedQuery("Show.findShowWithReservations", Show.class).setParameter("showId", showId);
        return query.getResultList().get(0);
    }
}
