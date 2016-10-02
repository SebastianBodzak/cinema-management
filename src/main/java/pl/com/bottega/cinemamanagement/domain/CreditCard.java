package pl.com.bottega.cinemamanagement.domain;

/**
 * Created by ulvar on 01.10.2016.
 */
public class CreditCard {

    private String number;
    private Integer expirationMonth;
    private Integer expirationYear;
    private Integer cvc;

    private CreditCard() {
    }

    public CreditCard(String number, Integer expirationMonth, Integer expirationYear, Integer cvc) {
        this.number = number;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.cvc = cvc;
    }

    public String getNumber() {
        return number;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public Integer getCvc() {
        return cvc;
    }
}
