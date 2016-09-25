package pl.com.bottega.cinemamanagement.domain;

import java.util.Set;

/**
 * Created by ulvar on 25.09.2016.
 */
public class CinemaHall {

    private boolean[][] occupiedSeats;

    public CinemaHall(Set<Reservation> reservations) {
        this.occupiedSeats = createOccupiedSeats(reservations);
    }

    private boolean[][] createOccupiedSeats(Set<Reservation> reservations) { //// TODO: 25.09.2016

        return null;
    }

    public boolean checkIfSeatsCanBeReserved(Set<Seat> seats) {

        return false;
    }

    public Set<Seat> getFreeSeats() {

        return null;
    }

    public Set<Seat> getOccupiedSeats() {

        return null;
    }


}
