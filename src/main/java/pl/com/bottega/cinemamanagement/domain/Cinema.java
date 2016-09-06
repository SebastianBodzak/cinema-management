package pl.com.bottega.cinemamanagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by bartosz.paszkowski on 04.09.2016.
 */
@Entity
public class Cinema {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String city;

    private Cinema() {}

    public Cinema(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CinemaCinema{" +
                "city ='" + city + '\'' +
                ", name ='" + name + '\'' +
                '}';
    }
}
