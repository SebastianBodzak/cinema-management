package pl.com.bottega.cinemamanagement.api;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
public class SeatDto {
    private Integer row;
    private Integer seat;


    public void validate(){

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
