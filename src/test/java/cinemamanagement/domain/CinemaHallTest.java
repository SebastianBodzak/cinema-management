package cinemamanagement.domain;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.cinemamanagement.domain.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by ulvar on 25.09.2016.
 */
public class CinemaHallTest {

    private Set<Reservation> reservations = new HashSet<>();
    private CinemaHall cinemaHall;

    @Before
    public void setUp() {
        Reservation reservation = createReservation(new Seat(1, 12), new Seat(1, 13));
        Reservation reservation2 = createReservation(new Seat(2, 11), new Seat(2, 12));
        reservations.add(reservation);
        reservations.add(reservation2);

    }

    @Test
    public void shouldCheckIfSeatsCanBeReserved() {
        cinemaHall = new CinemaHall(reservations);

        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 1), new Seat(1, 2), new Seat(1, 3)));

        assertTrue(result);
    }

    @Test
    public void shouldFailToMakeReservationBecauseItsAlreadyReserved() {
        cinemaHall = new CinemaHall(reservations);
        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 12), new Seat(1, 9), new Seat(1, 8)));
        assertFalse(result);
    }

    private Reservation createReservation(Seat... seat) {
        Set<TicketOrder> ticketOrders = new HashSet<>(Arrays.asList(new TicketOrder("regular", 10), new TicketOrder("student", 15)));
        Set<Seat> seats = new HashSet<>();
        Customer customer = new Customer("jan", "kowalski", "mail@mail.com", "+486439543039");
        BigDecimal totalPrice = new BigDecimal(100);

        for (Seat s : seat)
            seats.add(s);

        return new Reservation(ticketOrders, seats, customer, totalPrice);
    }
}
