package pl.com.bottega.cinemamanagement.api.responses;

import pl.com.bottega.cinemamanagement.domain.ReservationStatus;

/**
 * Created by bartosz.paszkowski on 02.10.2016.
 */
public class CollectPaymentResponse {
    private ReservationStatus reservationStatus;

    public CollectPaymentResponse(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }
}
