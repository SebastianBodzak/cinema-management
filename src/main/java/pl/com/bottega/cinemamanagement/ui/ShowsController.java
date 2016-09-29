package pl.com.bottega.cinemamanagement.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemamanagement.api.AdminPanel;
import pl.com.bottega.cinemamanagement.api.ReservationManager;
import pl.com.bottega.cinemamanagement.api.requests.CreateShowRequest;
import pl.com.bottega.cinemamanagement.api.responses.ListSeatsResponse;

/**
 * Created by Dell on 2016-09-08.
 */
@RestController
@RequestMapping("/shows")
public class ShowsController {

    private ReservationManager reservationManager;

    public ShowsController(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    @GetMapping("/{showId}/seats")
    public ListSeatsResponse listSeats(@PathVariable Long showId){
        return reservationManager.listFreeAndOccupiedSeats(showId);
    }
}
