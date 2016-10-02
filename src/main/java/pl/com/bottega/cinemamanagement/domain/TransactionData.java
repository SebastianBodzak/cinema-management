package pl.com.bottega.cinemamanagement.domain;

import javax.persistence.Embeddable;

/**
 * Created by ulvar on 01.10.2016.
 */
@Embeddable
public class TransactionData {

    private String status;
    private String number;

    public TransactionData(String status, String number) {
        this.status = status;
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public String getNumber() {
        return number;
    }
}
