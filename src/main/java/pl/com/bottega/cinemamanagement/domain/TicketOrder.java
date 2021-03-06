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
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public TicketOrder() {
    }

    public TicketOrder(String kind, Integer count) {
        this.kind = kind;
        this.quantity = count;
    }

    public String getKind() {
        return kind;
    }

    public Integer getCount() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void calculateTotalPrice() {
        this.totalPrice = unitPrice.multiply(new BigDecimal(quantity));
    }


}
