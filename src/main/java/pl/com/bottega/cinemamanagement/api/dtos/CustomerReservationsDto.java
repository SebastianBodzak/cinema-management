package pl.com.bottega.cinemamanagement.api.dtos;

import pl.com.bottega.cinemamanagement.domain.Reservation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dell on 2016-09-28.
 */
public class CustomerReservationsDto {

    private List<ReservationsDto> reservations;

    public CustomerReservationsDto(List<Reservation> reservations) {
        this.reservations = prepareReservationsDtoSet(reservations);
    }

    private List<ReservationsDto> prepareReservationsDtoSet(List<Reservation> reservations){
        List<ReservationsDto> reservationsDtoSet = new ArrayList<>();
        for (Reservation reservation : reservations){
            reservationsDtoSet.add(new ReservationsDto(reservation));
        }
        return reservationsDtoSet;
    }

    public List<ReservationsDto> getReservations() {
        return reservations;
    }
}