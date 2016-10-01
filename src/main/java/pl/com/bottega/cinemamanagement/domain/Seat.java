package pl.com.bottega.cinemamanagement.domain;

import com.google.common.base.Objects;

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

    public Seat() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equal(row, seat.row) &&
                Objects.equal(number, seat.number);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(row, number);
    }
}


