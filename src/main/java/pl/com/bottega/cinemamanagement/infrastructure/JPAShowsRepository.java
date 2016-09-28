package pl.com.bottega.cinemamanagement.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemamanagement.domain.Show;
import pl.com.bottega.cinemamanagement.domain.repositories.ShowsRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public Show findShowWithTicketPrices(Long showId) {
        List<Show> result = entityManager.createNamedQuery("Show.findShowWithTicketPrices", Show.class).setParameter("showId", showId).getResultList();
        return Utils.returnSingleResult(result);
    }

    @Override
    public Show findShowWithReservations(Long showId) {
        List<Show> result = entityManager.createNamedQuery("Show.findShowWithReservations", Show.class).setParameter("showId", showId).getResultList();
        return Utils.returnSingleResult(result);
    }
}
