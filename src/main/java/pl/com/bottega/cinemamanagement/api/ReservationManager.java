package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.ReservationRepository;
import pl.com.bottega.cinemamanagement.domain.Show;
import pl.com.bottega.cinemamanagement.domain.ShowsRepository;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
public class ReservationManager {

    private Show show;
    private ReservationRepository reservationRepository;
    private ShowsRepository showsRepository;

    public CreateReservationResponse createReservation(CreateReservationRequest request){

        return null;
    }
}
