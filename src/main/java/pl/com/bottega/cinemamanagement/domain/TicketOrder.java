package pl.com.bottega.cinemamanagement.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by Bartosz on 2016-09-19.
 */
@Entity
public class TicketOrder {
    @Id
    @GeneratedValue
    private Long id;

    private String kind;
    private Integer count;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public TicketOrder(String kind, Integer count) {
        this.kind = kind;
        this.count = count;
    }

    public String getKind() {
        return kind;
    }

    public Integer getCount() {
        return count;
    }

    public BigDecimal getUntilPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void calculateTotalPrice() {
        this.totalPrice = unitPrice.multiply(new BigDecimal(count));
    }


}
