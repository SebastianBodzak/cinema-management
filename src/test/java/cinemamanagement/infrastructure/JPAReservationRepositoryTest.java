package cinemamanagement.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.com.bottega.cinemamanagement.api.ReservationCriteria;
import pl.com.bottega.cinemamanagement.domain.Reservation;
import pl.com.bottega.cinemamanagement.domain.ReservationStatus;
import pl.com.bottega.cinemamanagement.infrastructure.JPAReservationRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bartosz on 2016-09-29.
 */
//@RunWith(SpringRunner.class)
//@ContextConfiguration("/application.xml")
//@WebAppConfiguration
//@Sql("/fixtures/reservations.sql")
public class JPAReservationRepositoryTest {

//    @Autowired
    private JPAReservationRepository jpaReservationRepository;

    private String lastName = "Doe";

//    @Test
//    @Sql("/fixtures/reservations.sql")
    public void shouldFindActualReservations(){


//        ReservationCriteria criteria = new ReservationCriteria();
//        criteria.setLastName(lastName);
//        criteria.setStatus(ReservationStatus.PENDING);
//
//        List<Reservation> reservation = jpaReservationRepository.findActualReservations(criteria);
//
//        assertEquals(lastName, reservation.get(0).getCustomer().getLastName());
//        assertEquals(ReservationStatus.PENDING, reservation.get(0).getStatus());

    }

}
