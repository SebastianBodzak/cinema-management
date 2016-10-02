package pl.com.bottega.cinemamanagement.infrastructure;

import pl.com.bottega.cinemamanagement.api.ReservationCriteria;
import pl.com.bottega.cinemamanagement.api.ReservationFindDto;
import pl.com.bottega.cinemamanagement.domain.Customer_;
import pl.com.bottega.cinemamanagement.domain.Reservation;
import pl.com.bottega.cinemamanagement.domain.Reservation_;
import pl.com.bottega.cinemamanagement.domain.Show_;
import pl.com.bottega.cinemamanagement.domain.repositories.ReservationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ulvar on 25.09.2016.
 */
//@Repository
public class JPAReservationRepositoryCriteriaBuilder implements ReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public List<Reservation> findActualReservations(ReservationCriteria criteria) {
        checkNotNull(criteria);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReservationFindDto> query = builder.createQuery(ReservationFindDto.class);
        Root<Reservation> root = query.from(Reservation.class);

        selectReservationFindDto(builder, query, root);
        applyCriteria(criteria, builder, query, root);

        Query jpaQuery = entityManager.createQuery(query);
        //ReservationSearchResult result= new ReservationSearchResult(jpaQuery.getResultList());
        return null;
    }

    @Override
    public Reservation findReservationByNumber(Long reservationNumber) {
        return null;
    }

    private void selectReservationFindDto(CriteriaBuilder builder, CriteriaQuery<ReservationFindDto> query, Root<Reservation> root) {
        query.select(builder.construct(ReservationFindDto.class,
                root.get(Reservation_.reservationNumber),
                root.get(Reservation_.show),
                root.get(Reservation_.show).get(Show_.movie),
                //root.get(Reservation_.ticketsOrder),
                //root.get(Reservation_.seats),
                root.get(Reservation_.customer),
                root.get(Reservation_.status)
                //root.get(Reservation_.totalPrice)
        )).distinct(true);
    }

    private void applyCriteria(ReservationCriteria criteria, CriteriaBuilder builder, CriteriaQuery<ReservationFindDto> query, Root<Reservation> root) {
        Collection<Predicate> predicates = new HashSet<>();
        addReservationIfLastNameIsDefined(criteria, builder, root, predicates);
        addReservationIfStratusIsDefined(criteria, builder, root, predicates);

        query.where(predicates.toArray(new Predicate[]{}));
    }

    private void addReservationIfLastNameIsDefined(ReservationCriteria criteria, CriteriaBuilder builder, Root<Reservation> root, Collection<Predicate> predicates) {
        if (criteria.isLastNameDefined())
            predicates.add(builder.equal(root.get(Reservation_.customer).get(Customer_.lastName), criteria.getLastName()));
    }

    private void addReservationIfStratusIsDefined(ReservationCriteria criteria, CriteriaBuilder builder, Root<Reservation> root, Collection<Predicate> predicates) {
        if (criteria.isStatusDefined())
            predicates.add(builder.equal(root.get(Reservation_.status), criteria.getStatus()));
    }
}
