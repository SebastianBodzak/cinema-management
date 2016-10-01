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

    public static final int ROWS = 10;
    public static final int SEATS = 15;

    private boolean[][] occupiedSeats = new boolean[ROWS][SEATS];

    public CinemaHall(Set<Reservation> reservations) {
        checkNotNull(reservations);

        createOccupiedSeats(reservations);
    }

    private void createOccupiedSeats(Set<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            Set<Seat> seatsSet = reservation.getSeats();
            for (Seat seat : seatsSet)
                occupiedSeats[seat.getRow() - 1][seat.getNumber() - 1] = true;
        }
    }

    public boolean checkIfSeatsCanBeReserved(Set<Seat> seatsSet) {
        checkNotNull(seatsSet);
        checkArgument(!seatsSet.isEmpty());

        if (checkIfBookedSeatsAreOccupied(seatsSet)) return false;
        if (checkIfSeatsAreInSameRow(seatsSet)) {
            List<Seat> seatsList = seatsSet.stream().sorted((e1, e2) -> Integer.compare(e1.getNumber(), e2.getNumber())).collect(Collectors.toList());
            if (!checkIfSeatsHaveCorrectOrder(seatsList))
                return noAvailableSeatsInSameRow(seatsList.size());
            return true;
        } else {
            return noAvailableSeatsInSameRow(seatsSet.size());
        }
    }

    public Set<Seat> getFreeSeats() {
        return checkSeatsState(false);
    }

    public Set<Seat> getOccupiedSeats() {
        return checkSeatsState(true);
    }

    private boolean noAvailableSeatsInSameRow(int seatsCount) {
        for (int rowCounter = 0; rowCounter < ROWS; rowCounter++)
            for (int seatsCounter = 0; seatsCounter < SEATS; seatsCounter++) {
                if (seatsCounter + seatsCount < SEATS) {
                    int freeSeatsCounter = 0;
                    for (int counter = seatsCounter; counter < seatsCounter + seatsCount; counter++) {
                        if (!occupiedSeats[rowCounter][counter])
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

    private boolean checkIfBookedSeatsAreOccupied(Set<Seat> seatsSet) {
        for (Seat seat : seatsSet)
            if (occupiedSeats[seat.getRow() - 1][seat.getNumber() - 1])
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

    private Set<Seat> checkSeatsState(boolean occupied) {
        Set<Seat> seatsSet = new LinkedHashSet<>();
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < SEATS; j++)
                if (occupiedSeats[i][j] == occupied)
                    seatsSet.add(new Seat(i + 1, j + 1));
        return seatsSet;
    }
}