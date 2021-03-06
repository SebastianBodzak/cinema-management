package pl.com.bottega.cinemamanagement.api.dtos;

import pl.com.bottega.cinemamanagement.domain.CreditCard;

/**
 * Created by arkadiuszarak on 01/10/2016.
 */
public class PaymentDto {
    private String type;
    private Long cashierId;
    private CreditCard creditCard;

    private PaymentDto() {
    }

    public PaymentDto(Long cashierId, CreditCard creditCard, String type) {
        this.cashierId = cashierId;
        this.creditCard = creditCard;
        this.type = type;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void validate() {
    }
}
