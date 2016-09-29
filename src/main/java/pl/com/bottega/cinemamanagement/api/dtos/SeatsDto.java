package pl.com.bottega.cinemamanagement.api.dtos;

import java.util.Set;

/**
 * Created by arkadiuszarak on 29/09/2016.
 */
public class SeatsDto {
    private Set<SeatDto> free;
    private Set<SeatDto> occupied;

    public SeatsDto() {
    }

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
