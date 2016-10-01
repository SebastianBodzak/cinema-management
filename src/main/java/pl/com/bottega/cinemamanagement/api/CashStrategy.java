package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.api.dtos.PaymentDto;
import pl.com.bottega.cinemamanagement.domain.Payment;

/**
 * Created by arkadiuszarak on 01/10/2016.
 */
public class CashStrategy implements PaymentStrategy {
    @Override
    public Payment pay(PaymentDto paymentDto) {
        return null;
    }
}
