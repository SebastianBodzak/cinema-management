package cinemamanagement.domain;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
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
@RunWith(MockitoJUnitRunner.class)
public class CinemaHallTest {

    private Set<Reservation> reservations = new HashSet<>();
    private Set<Reservation> emptyReservations = new HashSet<>();
    private CinemaHall cinemaHall;

    @Mock
    private Show show;

    @Before
    public void setUp() {
        Reservation reservation = createReservation(new Seat(1, 12), new Seat(1, 13));
        Reservation reservation2 = createReservation(new Seat(2, 11), new Seat(2, 12));
        reservations.add(reservation);
        reservations.add(reservation2);

    }

    @Test
    public void shouldReserveFullRow(){   //TODO tu nie powinno przejśc raczej, bo test poniżej wywala to czemu tu nagle działa?
        cinemaHall = new CinemaHall(emptyReservations);

        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 1), new Seat(1, 2), new Seat(1, 3),
                new Seat(1, 4), new Seat(1, 5), new Seat(1, 6), new Seat(1, 7), new Seat(1, 8), new Seat(1, 9), new Seat(1, 10),
                new Seat(1, 11), new Seat(1, 12), new Seat(1, 13), new Seat(1, 14), new Seat(1, 15)));  //TODO no i przepuszcza miejsca ktore jużsą zarezerwowane 1,12 1,13
        assertTrue(result);  //TODO Jak test leci sam to przechodzi, jak leci cała klasa testów to wywala out of bound ex row/seat.
    }

    @Test
    public void shouldReserveLastRowAndSeat(){
        cinemaHall = new CinemaHall(reservations);

        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(10, 15))); //TODO ROW=10 - out of bound?? seat=15 - out of bound exception??
        assertTrue(result);
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
        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 12), new Seat(1, 13), new Seat(1, 8))); //TODO czemu z 1,8 jest ok, a z 1,14 już nie?
        assertFalse(result);
    }

    @Test
    public void shouldFailToReserveDueToDifferentRows() {
        cinemaHall = new CinemaHall(reservations);
        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 4), new Seat(6, 5)));
        assertFalse(result);

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldFailToReserveBecauseOfNonExistingSeats() {
        cinemaHall = new CinemaHall(reservations);
        cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 16), new Seat(11, 9)));

    }

    @Test
    public void shouldFailToReserveDueSeatsNotNextToEachOther() {
        cinemaHall = new CinemaHall(reservations);
        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 10), new Seat(1, 11), new Seat(1, 14)));
        assertFalse(result);
    }


    private Reservation createReservation(Seat... seat) {
        Set<TicketOrder> ticketOrders = new HashSet<>(Arrays.asList(new TicketOrder("regular", 10), new TicketOrder("student", 15)));
        Set<Seat> seats = new HashSet<>();
        Customer customer = new Customer("jan", "kowalski", "mail@mail.com", "+486439543039");
        BigDecimal totalPrice = new BigDecimal(100);
        Show show = new Show();
        for (Seat s : seat)
            seats.add(s);

        return new Reservation(show, ticketOrders, seats, customer, totalPrice);
    }
}
