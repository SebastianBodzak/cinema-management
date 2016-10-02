package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.api.payment.strategies.CreditCardStrategy;
import pl.com.bottega.cinemamanagement.api.requests.CollectPaymentRequest;
import pl.com.bottega.cinemamanagement.api.responses.CollectPaymentResponse;
import pl.com.bottega.cinemamanagement.domain.Payment;
import pl.com.bottega.cinemamanagement.domain.Reservation;
import pl.com.bottega.cinemamanagement.domain.ReservationStatus;
import pl.com.bottega.cinemamanagement.domain.repositories.ReservationRepository;

/**
 * Created by arkadiuszarak on 01/10/2016.
 */
@Service
public class PaymentManager {
    private ReservationRepository reservationRepository;
    private EmailFacade emailFacade;

    public PaymentManager(EmailFacade emailFacade, ReservationRepository reservationRepository) {
        this.emailFacade = emailFacade;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public CollectPaymentResponse collectPayment(Long reservationNumber, CollectPaymentRequest request) {
        Reservation reservation = reservationRepository.findReservationByNumber(reservationNumber);
        if (reservation == null)
            throw new InvalidRequestException("There is no such reservation");
        if (reservation.getStatus().equals(ReservationStatus.PAID) || reservation.getStatus().equals(ReservationStatus.PAYMENT_FAILED))
            throw new InvalidRequestException("Reservation was payed or canceled");

        Payment payment = choosePaymentStrategy(request).pay(request.getPaymentDto(), reservation);
        reservation.addPayment(payment);

        ifPaymentByCreditCardSendTicketsViaMail(request, reservation);

        return new CollectPaymentResponse(reservation.getStatus());
    }

    private void ifPaymentByCreditCardSendTicketsViaMail(CollectPaymentRequest request, Reservation reservation) {
        if (request.getPaymentDto().getCreditCard() != null)
            emailFacade.sendTickets(reservation);
    }

    private PaymentStrategy choosePaymentStrategy(CollectPaymentRequest request) {
        if (request.getPaymentDto().getCreditCard() != null)
            return new CreditCardStrategy();
        else
            return new pl.com.bottega.cinemamanagement.api.CashStrategy();
    }
}
