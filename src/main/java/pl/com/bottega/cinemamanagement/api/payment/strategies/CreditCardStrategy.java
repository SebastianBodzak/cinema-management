package pl.com.bottega.cinemamanagement.api.payment.strategies;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import pl.com.bottega.cinemamanagement.api.*;
import pl.com.bottega.cinemamanagement.api.dtos.PaymentDto;
import pl.com.bottega.cinemamanagement.domain.CreditCard;
import pl.com.bottega.cinemamanagement.domain.Payment;
import pl.com.bottega.cinemamanagement.domain.Reservation;
import pl.com.bottega.cinemamanagement.domain.TransactionData;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static pl.com.bottega.cinemamanagement.domain.PaymentType.*;
/**
 * Created by arkadiuszarak on 01/10/2016.
 */
public class CreditCardStrategy implements PaymentStrategy {

    private final String apiKey = "sk_test_4bZI3oYWTX1oL37RGfJAQaqF";

    @Override
    public Payment pay(PaymentDto paymentDto, Reservation reservation) {

        Stripe.apiKey = apiKey;

        Map<String, Object> source = new HashMap<>();
        source.put("exp_month", paymentDto.getCreditCard().getExpirationMonth());
        source.put("exp_year", paymentDto.getCreditCard().getExpirationYear());
        source.put("number", paymentDto.getCreditCard().getNumber());
        source.put("cvc", paymentDto.getCreditCard().getCvc());
        source.put("object", "card");

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", getTotalPriceInCents(reservation.getTotalPrice()));
        chargeParams.put("currency", "pln");
        chargeParams.put("source", source); // obtained with Stripe.js
        chargeParams.put("description", "Charge for " + reservation.getCustomer().getEmail());

        try {
            Charge chargeData = Charge.create(chargeParams);
            TransactionData transactionData = new TransactionData(chargeData.getStatus().toUpperCase(), chargeData.getId());
            return new Payment(CREDIT_CARD, null, true, transactionData);
        } catch (AuthenticationException e) {
            throw new pl.com.bottega.cinemamanagement.api.InvalidRequestException("AuthenticationException");
        } catch (InvalidRequestException e) {
            throw new pl.com.bottega.cinemamanagement.api.InvalidRequestException("InvalidRequestException (Stripe library)");
        } catch (APIConnectionException e) {
            throw new pl.com.bottega.cinemamanagement.api.InvalidRequestException("APIConnectionException ");
        } catch (CardException e) {
            throw new pl.com.bottega.cinemamanagement.api.InvalidRequestException("CardException" + e.getCode());
        } catch (APIException e) {
            throw new pl.com.bottega.cinemamanagement.api.InvalidRequestException("APIException");
        }
    }

    private BigInteger getTotalPriceInCents(BigDecimal bigDecimal) {
        return bigDecimal.multiply(new BigDecimal(100)).toBigInteger();
    }
}
