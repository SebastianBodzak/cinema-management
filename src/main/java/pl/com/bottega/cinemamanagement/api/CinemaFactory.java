package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemamanagement.domain.Cinema;

/**
 * Created by Dell on 2016-09-06.
 */
@Component
public class CinemaFactory {

    public Cinema create(String name, String city) {
        return new Cinema(name, city);
    }

}
