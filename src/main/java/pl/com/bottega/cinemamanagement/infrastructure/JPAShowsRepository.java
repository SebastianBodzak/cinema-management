package pl.com.bottega.cinemamanagement.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemamanagement.domain.Show;
import pl.com.bottega.cinemamanagement.domain.repositories.ShowsRepository;
import pl.com.bottega.cinemamanagement.domain.TicketPrice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Set;

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
    public Set<TicketPrice> listTicketPrices(Long showId) {

        String jpa = "SELECT DISTINCT sh FROM Show sh " +
                "JOIN FETCH sh.movie m " +
                "JOIN FETCH m.ticketPrices tp " +
                "WHERE sh.id = :showId";

        TypedQuery<Show> query = entityManager.createQuery(jpa, Show.class);
        query.setParameter("showId", showId);
        Show show = query.getResultList().get(0);
        Set<TicketPrice> prices;
        prices = show.getMovie().getTicketPrices();
        return prices;
    }

    @Override
    public Show findShowWithReservations(Long showId) {
        String jpa = "SELECT DISTINCT sh FROM Show sh " +
                "JOIN FETCH sh.movie m " +
                "JOIN FETCH m.ticketPrices tp " +
                "LEFT JOIN FETCH sh.reservations r " +
                "WHERE sh.id = :showId";
        Query query = entityManager.createQuery(jpa);
        query.setParameter("showId", showId);
        Show show = (Show) query.getResultList().get(0);
        return show;
    }
}
