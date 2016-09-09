package pl.com.bottega.cinemamanagement.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemamanagement.api.CinemaCatalog;
import pl.com.bottega.cinemamanagement.api.CinemaDto;
import pl.com.bottega.cinemamanagement.api.ListAllCinemasResponse;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.Cinema_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by ulvar on 07.09.2016.
 */
@Component
public class JPACinemaCatalog implements CinemaCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListAllCinemasResponse listAll() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CinemaDto> query = builder.createQuery(CinemaDto.class);
        Root<Cinema> root = query.from(Cinema.class);
        selectCinemaDto(builder, query,root);
        Query jpaQuery = entityManager.createQuery(query);

        return new ListAllCinemasResponse(jpaQuery.getResultList());
    }

    private void selectCinemaDto(CriteriaBuilder builder, CriteriaQuery<CinemaDto> query, Root<Cinema> root){
        query.select(builder.construct(CinemaDto.class,
                root.get(Cinema_.id),
                root.get(Cinema_.name),
                root.get(Cinema_.city)
        ));
    }
}
