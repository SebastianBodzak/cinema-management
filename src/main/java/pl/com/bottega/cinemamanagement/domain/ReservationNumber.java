package pl.com.bottega.cinemamanagement.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by ulvar on 25.09.2016.
 */
@Embeddable
public class ReservationNumber implements Serializable {

    private String number;

    public ReservationNumber() {
        this.number = UUID.randomUUID().toString();
    }
}
