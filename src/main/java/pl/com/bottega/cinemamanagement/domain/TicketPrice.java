package pl.com.bottega.cinemamanagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by bartosz.paszkowski on 18.09.2016.
 */
@Entity
public class TicketPrice {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private BigDecimal price;

    public TicketPrice(String type, BigDecimal price) {
        this.type = type;
        this.price = price;
    }

    public TicketPrice() {
    }

    public String getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
