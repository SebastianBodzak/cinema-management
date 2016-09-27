package pl.com.bottega.cinemamanagement.domain;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinema", fetch = FetchType.EAGER)
    private Set<Show> shows;


    public Cinema() {
    }

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

    public Long getId() {
        return id;
    }

    public Set<Show> getShows() {
        return shows;
    }

    @Override
    public String toString() {
        return "CinemaCinema{" +
                "city ='" + city + '\'' +
                ", name ='" + name + '\'' +
                '}';
    }
}
