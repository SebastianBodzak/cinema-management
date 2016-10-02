package pl.com.bottega.cinemamanagement.api.requests;

import pl.com.bottega.cinemamanagement.api.dtos.PaymentDto;

/**
 * Created by arkadiuszarak on 01/10/2016.
 */
public class CollectPaymentRequest {
    private PaymentDto payment;

    public PaymentDto getPaymentDto() {
        return payment;
    }

    public void setPayment(PaymentDto paymentDto) {
        this.payment = paymentDto;
    }

    public void validate() {
    }
}
