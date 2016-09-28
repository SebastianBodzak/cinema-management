package cinemamanagement.domain;

import com.google.common.collect.Sets;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.domain.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by ulvar on 25.09.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CinemaHallTest {

    private final Seat seat1_12 = new Seat(1, 12);
    private final Seat seat1_13 = new Seat(1, 13);
    private final Seat seat2_11 = new Seat(2, 11);
    private final Seat seat2_12 = new Seat(2, 12);
    private Set<Reservation> reservations = new HashSet<>();
    private Set<Reservation> emptyReservations = new HashSet<>();
    private CinemaHall cinemaHall;

    @Mock
    private Show show;

    @Mock
    private Customer customer;

    @Before
    public void setUp() {
        Reservation reservation = createReservation(seat1_12, seat1_13);
        Reservation reservation2 = createReservation(seat2_11, seat2_12);
        reservations.add(reservation);
        reservations.add(reservation2);
        cinemaHall = new CinemaHall(reservations);
    }

    @Test
    public void shouldReserveFullRow(){
        cinemaHall = new CinemaHall(emptyReservations);

        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 1), new Seat(1, 2), new Seat(1, 3),
                new Seat(1, 4), new Seat(1, 5), new Seat(1, 6), new Seat(1, 7), new Seat(1, 8), new Seat(1, 9), new Seat(1, 10),
                new Seat(1, 11), new Seat(1, 12), new Seat(1, 13), new Seat(1, 14), new Seat(1, 15)));

        assertTrue(result);
    }

    @Test
    public void shouldReserveLastRowAndSeat(){
        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(10, 15)));

        assertTrue(result);
    }



    @Test
    public void shouldCheckIfSeatsCanBeReserved() {
        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 1), new Seat(1, 2), new Seat(1, 3)));

        assertTrue(result);
    }

    @Test
    public void shouldFailToMakeReservationBecauseItsAlreadyReserved() {
        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 12), new Seat(1, 13), new Seat(1, 14)));

        assertFalse(result);
    }

    @Test
    public void shouldFailToReserveDueToDifferentRows() {
        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 4), new Seat(6, 5)));

        assertFalse(result);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldFailToReserveBecauseOfNonExistingSeats() {
        cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 16), new Seat(11, 9)));
    }

    @Test
    public void shouldFailToReserveDueSeatsNotNextToEachOther() {
        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 10), new Seat(1, 11), new Seat(1, 14)));

        assertFalse(result);
    }

    @Test
    public void shouldBookedSeatsInDifferentRowsBecauseOfLackOfSeatsInSameRow() {
        cinemaHall = new CinemaHall(fillAllCinemaHallWithoutSeats(new Seat(1, 1), new Seat(1, 2), new Seat(2, 1), new Seat(2, 2)));

        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 1), new Seat(2, 1), new Seat(2, 2)));

        assertTrue(result);
    }

    @Test
    public void shouldBookedSeatsInSameRowWithIncorrectOrderBecauseOfLackOfSeatsInSameRow() {
        cinemaHall = new CinemaHall(fillAllCinemaHallWithoutSeats(new Seat(1, 1), new Seat(1, 2), new Seat(1, 4), new Seat(1, 5)));

        boolean result = cinemaHall.checkIfSeatsCanBeReserved(Sets.newHashSet(new Seat(1, 1), new Seat(1, 2), new Seat(1, 4)));

        assertTrue(result);
    }

    @Test
    public void shouldReturnFreeSeats() {
        Set<Seat> freeSeats = cinemaHall.getFreeSeats();

        assertEquals(146, freeSeats.size());
        assertTrue(!freeSeats.contains(seat1_12));
        assertTrue(!freeSeats.contains(seat1_13));
        assertTrue(!freeSeats.contains(seat2_11));
        assertTrue(!freeSeats.contains(seat2_12));
    }

    @Test
    public void shouldReturnOccupiedSeats() {
        Set<Seat> occupiedSeats = cinemaHall.getOccupiedSeats();

        assertEquals(4, occupiedSeats.size());
        assertTrue(occupiedSeats.contains(seat1_12));
        assertTrue(occupiedSeats.contains(seat1_13));
        assertTrue(occupiedSeats.contains(seat2_11));
        assertTrue(occupiedSeats.contains(seat2_12));
    }

    private Set<Reservation> fillAllCinemaHallWithoutSeats(Seat ...seats) {
        Set<Seat> seatsSet = new LinkedHashSet<>();
        for (int rowCounter = 1; rowCounter <= 10; rowCounter++)
            for (int seatsCounter = 1; seatsCounter <= 15; seatsCounter++)
                seatsSet.add(new Seat(rowCounter, seatsCounter));

        Set<Seat> freeSeats = new LinkedHashSet<>();
        for (Seat seat : seats)
            freeSeats.add(seat);

        Set<Seat> seatsSetWithFreeSeats = seatsSet.stream().filter(s -> !freeSeats.contains(s)).collect(Collectors.toSet());

        return Sets.newHashSet(new Reservation(show, Sets.newHashSet(), seatsSetWithFreeSeats, customer, new BigDecimal(1000L)));
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
