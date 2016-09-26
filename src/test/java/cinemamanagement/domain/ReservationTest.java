package cinemamanagement.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.domain.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by ulvar on 25.09.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationTest {

    @Mock
    private Show show;

    @Test
    public void shouldMakeReservation() {
        Set<TicketOrder> ticketOrders = new HashSet<>(Arrays.asList(new TicketOrder("regular", 10), new TicketOrder("student", 15)));
        Set<Seat> seats = new HashSet<>(Arrays.asList(new Seat(2, 2), new Seat(3, 3)));
        Customer customer = new Customer("jan", "kowalski", "mail@mail.com", "+486439543039");
        BigDecimal totalPrice = new BigDecimal(100);

        Reservation reservation = new Reservation(show, ticketOrders, seats, customer, totalPrice);

        assertEquals(ticketOrders, reservation.getTicketsOrder());
        assertEquals(seats, reservation.getSeats());
        assertEquals(customer, reservation.getCustomer());
        assertEquals(ReservationStatus.PENDING, reservation.getStatus());
    }
}
