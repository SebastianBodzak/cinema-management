package pl.com.bottega.cinemamanagement.api.dtos;

import pl.com.bottega.cinemamanagement.api.InvalidRequestException;

import static pl.com.bottega.cinemamanagement.domain.CinemaHall.*;
/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
public class SeatDto {

    private Integer row;
    private Integer seat;

    public SeatDto(Integer row, Integer seat) {
        this.row = row;
        this.seat = seat;
    }

    public SeatDto() {

    }

    public void validate() {
        if (row == null)
            throw new InvalidRequestException("Missing seat row");
        if (row < ROWS || row > ROWS)
            throw new InvalidRequestException("Invalid row. Chose row number between 1-10");
        if (seat == null)
            throw new InvalidRequestException("Missing seat number");
        if (seat < SEATS || seat > SEATS)
            throw new InvalidRequestException("Invalid seat. Chose seat number between 1-15");
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
