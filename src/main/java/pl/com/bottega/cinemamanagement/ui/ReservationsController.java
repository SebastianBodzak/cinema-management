package pl.com.bottega.cinemamanagement.ui;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemamanagement.api.PaymentManager;
import pl.com.bottega.cinemamanagement.api.ReservationCriteria;
import pl.com.bottega.cinemamanagement.api.ReservationManager;
import pl.com.bottega.cinemamanagement.api.TicketService;
import pl.com.bottega.cinemamanagement.api.dtos.CustomerReservationsDto;
import pl.com.bottega.cinemamanagement.api.requests.CollectPaymentRequest;
import pl.com.bottega.cinemamanagement.api.requests.CreateReservationRequest;
import pl.com.bottega.cinemamanagement.api.responses.CollectPaymentResponse;
import pl.com.bottega.cinemamanagement.api.responses.CreateReservationResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static pl.com.bottega.cinemamanagement.infrastructure.PdfFacadeImpl.DIRECTORY;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private ReservationManager reservationManager;
    private PaymentManager paymentManager;
    private TicketService ticketService;

    public ReservationsController(ReservationManager reservationManager, PaymentManager paymentManager, TicketService ticketService) {
        this.reservationManager = reservationManager;
        this.paymentManager = paymentManager;
        this.ticketService = ticketService;
    }

    @PutMapping
    public CreateReservationResponse create(@RequestBody CreateReservationRequest request) {
        return reservationManager.createReservation(request);
    }

    @GetMapping
    public CustomerReservationsDto findReservations(ReservationCriteria criteria) {
        return reservationManager.findReservations(criteria);
    }

    @PutMapping("/{reservationNumber}/payments")
    public CollectPaymentResponse create(@PathVariable Long reservationNumber, @RequestBody CollectPaymentRequest collectPaymentRequest) {
        return paymentManager.collectPayment(reservationNumber, collectPaymentRequest);
    }

    @GetMapping("/{reservationNumber}/tickets")
    public void printTicket(@PathVariable Long reservationNumber, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String fileName = ticketService.printTicket(reservationNumber);
        InputStream inputStream = new FileInputStream(DIRECTORY + fileName);
        OutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream, outputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
    }

}
