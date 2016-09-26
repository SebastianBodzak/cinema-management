package pl.com.bottega.cinemamanagement.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemamanagement.domain.Movie;
import pl.com.bottega.cinemamanagement.domain.repositories.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by arkadiuszarak on 04/09/2016.
 */
@Repository
public class JPAMoviesRepository implements MovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Movie movie) {
        entityManager.persist(movie);
    }

    @Override
    public Movie findById(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        return movie;
    }
}
