package pl.com.bottega.cinemamanagement.api;

import com.stripe.exception.*;
import pl.com.bottega.cinemamanagement.api.dtos.PaymentDto;
import pl.com.bottega.cinemamanagement.domain.Payment;
import pl.com.bottega.cinemamanagement.domain.Reservation;

/**
 * Created by arkadiuszarak on 01/10/2016.
 */
public interface PaymentStrategy {
    Payment pay(PaymentDto paymentDto, Reservation reservation);
}
