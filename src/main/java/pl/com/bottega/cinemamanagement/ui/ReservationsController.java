package pl.com.bottega.cinemamanagement.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemamanagement.api.CreateReservationRequest;
import pl.com.bottega.cinemamanagement.api.CreateReservationResponse;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    @PutMapping
    public CreateReservationResponse create(@RequestBody CreateReservationRequest request){

       return null;
    }
}
