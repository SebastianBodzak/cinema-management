package pl.com.bottega.cinemamanagement.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemamanagement.api.ReservationCriteria;
import pl.com.bottega.cinemamanagement.api.ReservationSearchResult;
import pl.com.bottega.cinemamanagement.api.dtos.CustomerReservationsDto;
import pl.com.bottega.cinemamanagement.api.requests.CreateReservationRequest;
import pl.com.bottega.cinemamanagement.api.responses.CreateReservationResponse;
import pl.com.bottega.cinemamanagement.api.ReservationManager;
import pl.com.bottega.cinemamanagement.api.responses.ListSeatsResponse;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private ReservationManager reservationManager;

    public ReservationsController(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    @PutMapping
    public CreateReservationResponse create(@RequestBody CreateReservationRequest request) {
        return reservationManager.createReservation(request);
    }

    @GetMapping
    public CustomerReservationsDto findReservations(ReservationCriteria criteria){
        return reservationManager.findReservation(criteria);
    }

}
