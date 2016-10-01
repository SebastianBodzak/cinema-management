package cinemamanagement.infrastructure;

import pl.com.bottega.cinemamanagement.infrastructure.JPAReservationRepository;

/**
 * Created by Bartosz on 2016-09-29.
 */
//@RunWith(SpringRunner.class)
//@ContextConfiguration("/application.xml")
//@WebAppConfiguration
public class JPAReservationRepositoryTest {

    //@Autowired
    private JPAReservationRepository jpaReservationRepository;

    private String lastName = "Doe";

    //@Test
    public void shouldTakeReservationFromDB(){
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
