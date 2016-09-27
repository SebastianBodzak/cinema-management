package pl.com.bottega.cinemamanagement.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemamanagement.api.responses.ListMoviesInCinemaResponse;
import pl.com.bottega.cinemamanagement.api.MovieCatalog;
import pl.com.bottega.cinemamanagement.domain.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by arkadiuszarak on 18/09/2016.
 */
@Component
public class JPAMovieCatalog implements MovieCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListMoviesInCinemaResponse listMoviesInCinema(Long cinemaId, LocalDate date) {

        String jpa = "SELECT DISTINCT m FROM Movie m " +
                "JOIN FETCH m.shows s " +
                "JOIN FETCH s.cinema c " +
                "JOIN FETCH m.actors " +
                "JOIN FETCH m.genres " +
                "WHERE c.id = :cinemaId AND s.date= :date " +
                " ORDER BY m.title";

        Query query = entityManager.createQuery(jpa);
        query.setParameter("cinemaId", cinemaId);
        query.setParameter("date", date);
        List<Movie> movies = query.getResultList();
        return new ListMoviesInCinemaResponse(movies);
    }
}
