package pl.com.bottega.cinemamanagement.domain;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;
/**
 * Created by ulvar on 25.09.2016.
 */
public class CinemaHall {

    private static final int rows = 10;
    private static final int seats = 15;
    private boolean[][] occupiedSeats = new boolean[rows][seats];

    public CinemaHall(Set<Reservation> reservations) {
        checkNotNull(reservations);

        createOccupiedSeats(reservations);
    }

    private void createOccupiedSeats(Set<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            Set<Seat> seatsSet = reservation.getSeats();
            for (Seat seat : seatsSet)
                occupiedSeats[seat.getRow()][seat.getNumber()] = true;
        }

    }

    public boolean checkIfSeatsCanBeReserved(Set<Seat> seatsSet) {
        checkNotNull(seatsSet);

        if (!checkIfBookedSeatsAreFree(seatsSet)) return false;
        if (checkIfSeatsAreInSameRow(seatsSet)) {
            List<Seat> seatsList = seatsSet.stream().sorted((e1, e2) -> Integer.compare(e1.getNumber(), e2.getNumber())).collect(Collectors.toList());
            if (!checkIfSeatsHaveCorrectOrder(seatsList))
                return noAvailableSeatsInSameRow(seatsList.size());
            return true;
        } else {
            return noAvailableSeatsInSameRow(seatsSet.size());
        }
    }

    private boolean noAvailableSeatsInSameRow(int seatsCount) {
        for (int rowCounter = 0; rowCounter < rows; rowCounter++)
            for (int seatsCounter = 0; seatsCount < seats; seatsCounter++) {
                if (seatsCounter + seatsCount < seats) {
                    int freeSeatsCounter = 0;
                    for (int counter = seatsCounter; counter < seatsCounter + seatsCount; counter++) {
                        if (!occupiedSeats[rowCounter][seatsCounter])
                            freeSeatsCounter++;
                        else
                            break;
                    }
                    if (freeSeatsCounter == seatsCount)
                        return false;
                }
            }
        return true;
    }

    private boolean checkIfBookedSeatsAreFree(Set<Seat> seatsSet) {
        for (Seat seat : seatsSet)
            if (occupiedSeats[seat.getRow()][seat.getNumber()] = true)
                return true;
        return false;
    }

    private boolean checkIfSeatsHaveCorrectOrder(List<Seat> seatsSet) {
        int i = seatsSet.iterator().next().getNumber();
        for (Seat seat : seatsSet) {
            if (!seat.getNumber().equals(i))
                return false;
            i++;
        }
        return true;
    }

    private boolean checkIfSeatsAreInSameRow(Set<Seat> seatsSet) {
        Integer row = seatsSet.iterator().next().getRow();
        return !seatsSet.stream().filter(e -> !e.getRow().equals(row)).findFirst().isPresent();
    }

    public Set<Seat> getFreeSeats() {
        return checkSeatsState(false);
    }

    public Set<Seat> getOccupiedSeats() {
        return checkSeatsState(true);
    }


    private Set<Seat> checkSeatsState(boolean occupied) {
        Set<Seat> seatsSet = new LinkedHashSet<>();
        for (int i = 0; i <= rows; i++)
            for (int j = 0; j <= seats; j++)
                if (occupiedSeats[i][j] == occupied)
                    seatsSet.add(new Seat(i, j));
        return seatsSet;
    }

}
