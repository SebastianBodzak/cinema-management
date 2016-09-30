package pl.com.bottega.cinemamanagement.api.responses;

import pl.com.bottega.cinemamanagement.api.dtos.SeatDto;

import java.util.Set;

/**
 * Created by arkadiuszarak on 28/09/2016.
 */
public class ListSeatsResponse {
    private SeatsDto seats;

    public ListSeatsResponse(Set<SeatDto> freeSeats, Set<SeatDto> occupiedSeats) {
        this.seats = new SeatsDto(freeSeats, occupiedSeats);
    }

    public SeatsDto getSeats() {
        return seats;
    }

    public void setSeats(SeatsDto seats) {
        this.seats = seats;
    }

    public class SeatsDto {
        private Set<SeatDto> free;
        private Set<SeatDto> occupied;

        public SeatsDto(Set<SeatDto> free, Set<SeatDto> occupied) {
            this.free = free;
            this.occupied = occupied;
        }

        public Set<SeatDto> getFree() {
            return free;
        }

        public void setFree(Set<SeatDto> free) {
            this.free = free;
        }

        public Set<SeatDto> getOccupied() {
            return occupied;
        }

        public void setOccupied(Set<SeatDto> occupied) {
            this.occupied = occupied;
        }
    }
}
