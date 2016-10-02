package pl.com.bottega.cinemamanagement.api;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.api.requests.CollectPaymentRequest;
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
    private PaymentStrategy paymentStrategy;

    private PaymentManager(){}

    public PaymentManager(EmailFacade emailFacade, PaymentStrategy paymentStrategy, ReservationRepository reservationRepository) {
        this.emailFacade = emailFacade;
        this.paymentStrategy = paymentStrategy;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public void collectPayment(Long reservationNumber, CollectPaymentRequest collectPaymentRequest){
        Reservation reservation = reservationRepository.findReservationByNumber(reservationNumber);
        if (reservation == null)
            throw new InvalidRequestException("There is no such reservation");
        if (!(reservation.getStatus() == ReservationStatus.PAID || reservation.getStatus() == ReservationStatus.PAYMENT_FAILED ))
            throw new InvalidRequestException("Reservation was payed or canceled");

        Payment payment = paymentStrategy.pay(collectPaymentRequest.getPaymentDto(), reservation);
        reservation.addPayment(payment);
    }
}
