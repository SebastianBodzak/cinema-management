package pl.com.bottega.cinemamanagement.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.CinemaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by ulvar on 04.09.2016.
 */
@Repository
public class JPACinemaRepository implements CinemaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Cinema cinema) {
        entityManager.persist(cinema);
    }

    @Override
    public Cinema load(String name, String city) {
        List<Cinema> cinemas = entityManager.createQuery("FROM Cinema c WHERE c.name =:name AND c.city =:city ", Cinema.class).setParameter("city", city)
                .setParameter("name", name).getResultList();
        if (cinemas.isEmpty())
            return null;
        return cinemas.get(0);
    }

    @Override
    public Cinema findById(Long cinemaId) {
        return entityManager.find(Cinema.class, cinemaId);
    }
}
