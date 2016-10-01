package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.api.dtos.PaymentDto;

/**
 * Created by arkadiuszarak on 01/10/2016.
 */
public class CreditCardStrategy implements PaymentStrategy {
    @Override
    public Payment pay(PaymentDto paymentDto) {
        return null;
    }
}
