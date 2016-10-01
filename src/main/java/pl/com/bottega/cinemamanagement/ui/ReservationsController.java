package pl.com.bottega.cinemamanagement.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemamanagement.api.requests.CollectPaymentRequest;
import pl.com.bottega.cinemamanagement.api.PaymentManager;
import pl.com.bottega.cinemamanagement.api.ReservationCriteria;
import pl.com.bottega.cinemamanagement.api.dtos.CustomerReservationsDto;
import pl.com.bottega.cinemamanagement.api.requests.CreateReservationRequest;
import pl.com.bottega.cinemamanagement.api.responses.CreateReservationResponse;
import pl.com.bottega.cinemamanagement.api.ReservationManager;

import java.io.File;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private ReservationManager reservationManager;
    private PaymentManager paymentManager;

    public ReservationsController(ReservationManager reservationManager, PaymentManager paymentManager) {
        this.reservationManager = reservationManager;
        this.paymentManager = paymentManager;
    }

    @PutMapping
    public CreateReservationResponse create(@RequestBody CreateReservationRequest request) {
        return reservationManager.createReservation(request);
    }

    @GetMapping
    public CustomerReservationsDto findReservations(ReservationCriteria criteria){
        return reservationManager.findReservations(criteria);
    }

    @PutMapping("/{reservationNumber}/payments")
    public void create(@PathVariable Long reservationNumber, @RequestBody CollectPaymentRequest collectPaymentRequest){
        paymentManager.collectPayment(reservationNumber, collectPaymentRequest);
    }

    @GetMapping("/{reservationNumber}/tickets")
    public File printTicket(@PathVariable Long reservationNumber){
        return null;
    }

}
