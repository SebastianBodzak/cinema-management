package cinemamanagement.api.payment.strategies;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.TransactionStatus;
import pl.com.bottega.cinemamanagement.api.dtos.PaymentDto;
import pl.com.bottega.cinemamanagement.api.payment.strategies.CreditCardStrategy;
import pl.com.bottega.cinemamanagement.domain.CreditCard;
import pl.com.bottega.cinemamanagement.domain.Customer;
import pl.com.bottega.cinemamanagement.domain.Payment;
import pl.com.bottega.cinemamanagement.domain.Reservation;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests have external dependencies (with Stripe application)
 * Created by Dell on 2016-10-02.
 */
public class CreditCardStrategyTest {

    private final BigDecimal totalPrice = new BigDecimal(30);
    private final String validCreditCardNumber = "4111111111111111";
    private final String declineCreditCardNumber = "4000000000000002";
    private final String incorrectCreditCardNumber = "4242424242424241";
    private CreditCardStrategy creditCardStrategy;
    private Long cashierId = 500L;
    private Integer expirationMonth = 10;
    private Integer expirationYear = 30;
    private Integer cvc = 232;
    private String type = "card";
    private Customer customer = new Customer("Janusz", "Kowalski", "email@Gmail.com", "123456789");

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        creditCardStrategy = new CreditCardStrategy();
    }

//    @Test
    public void shouldMakeSuccessfulTransaction() {
        CreditCard creditCard = new CreditCard(validCreditCardNumber, expirationMonth, expirationYear, cvc);
        PaymentDto paymentDto = new PaymentDto(cashierId, creditCard, type);

        Reservation reservation = new Reservation(null, null, null, customer, totalPrice);

        Payment result = creditCardStrategy.pay(paymentDto, reservation);

        assertEquals(TransactionStatus.SUCCEEDED.toString(), result.getTransactionData().getStatus());
        assertNotNull(result.getTransactionData().getNumber());
    }

//    @Test
    public void shouldDeclineTransactionAndThrowExceptionBecauseOfInvalidCreditCardNumber() throws InvalidRequestException {
        exception.expect(InvalidRequestException.class);
        CreditCard creditCard = new CreditCard(declineCreditCardNumber, expirationMonth, expirationYear, cvc);
        PaymentDto paymentDto = new PaymentDto(cashierId, creditCard, type);
        Reservation reservation = new Reservation(null, null, null, customer, totalPrice);
        exception.expectMessage("CardException");

        creditCardStrategy.pay(paymentDto, reservation);
    }

    @Test
    public void shouldThrowExceptionBecauseInvalidCreditCardNumber() {

    }

}
