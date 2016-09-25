package pl.com.bottega.cinemamanagement.api;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
public class SeatDto {
    private Integer row;
    private Integer seat;


    public void validate() {
        if (row == null)
            throw new InvalidRequestException("Missing seat row");
        if (row < 1 || row > 10)
            throw new InvalidRequestException("Invalid row. Chose row number between 1-10");
        if (seat == null)
            throw new InvalidRequestException("Missing seat number");
        if (seat < 1 || seat > 15)
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
