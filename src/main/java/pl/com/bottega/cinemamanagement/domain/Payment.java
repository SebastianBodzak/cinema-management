package pl.com.bottega.cinemamanagement.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by ulvar on 01.10.2016.
 */
@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    private Long cashierID;
    private LocalDateTime date;
    private boolean succesfull;
    @Embedded
    private TransactionData transactionData;

    private Payment() {}

    public Payment(PaymentType type, Long cashierID, boolean succesfull, TransactionData transactionData) {
        this.type = type;
        this.cashierID = cashierID;
        this.succesfull = succesfull;
        this.transactionData = transactionData;
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public PaymentType getType() {
        return type;
    }

    public Long getCashierID() {
        return cashierID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isSuccesfull() {
        return succesfull;
    }

    public TransactionData getTransactionData() {
        return transactionData;
    }
}
