package pl.com.bottega.cinemamanagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ulvar on 25.09.2016.
 */
@Entity
public class Seat {

    @Id
    @GeneratedValue
    private Long id;


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
                ", seat ='" + number + '\'' +
                '}';
    }


}


