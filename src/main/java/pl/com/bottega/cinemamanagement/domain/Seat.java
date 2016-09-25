package pl.com.bottega.cinemamanagement.domain;

/**
 * Created by ulvar on 25.09.2016.
 */

public class Seat {


    private Integer row;
    private Integer number;

    public Seat(Integer row, Integer number) {
        this.row = row;
        this.number = number;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "seats{" +
                "row ='" + row + '\'' +
                ", number ='" + number + '\'' +
                '}';
    }


}


