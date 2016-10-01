package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.api.dtos.PaymentDto;
import pl.com.bottega.cinemamanagement.domain.Payment;
import static pl.com.bottega.cinemamanagement.domain.PaymentType.CASH;

/**
 * Created by arkadiuszarak on 01/10/2016.
 */
public class CashStrategy implements PaymentStrategy {
    @Override
    public Payment pay(PaymentDto paymentDto) {

        return new Payment(CASH, paymentDto.getCashierId(), true, null);
    }
}
