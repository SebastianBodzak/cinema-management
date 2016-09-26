package pl.com.bottega.cinemamanagement.api.responses;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
public class CreateReservationResponse {

    private String reservationNumber;

    public CreateReservationResponse(Long reservationNumber) {
        this.reservationNumber = reservationNumber.toString();
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        reservationNumber = reservationNumber;
    }
}
