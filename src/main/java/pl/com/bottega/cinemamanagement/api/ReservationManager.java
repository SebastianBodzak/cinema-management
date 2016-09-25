package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.domain.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */

@Service
public class ReservationManager {

    //private Show show;

    private ReservationRepository reservationRepository;
    private ShowsRepository showsRepository;
    private PriceCalculator priceCalculator;

    public ReservationManager(ReservationRepository reservationRepository, ShowsRepository showsRepository, PriceCalculator priceCalculator) {
        this.reservationRepository = reservationRepository;
        this.showsRepository = showsRepository;
        this.priceCalculator = priceCalculator;
    }

    @Transactional
    public CreateReservationResponse createReservation(CreateReservationRequest request) {
        request.getReservation().validate();
        Show show = showsRepository.findShowWithReservations(request.getReservation().getShowId());
        Set<Reservation> reservations = show.getReservations();
        CinemaHall cinemaHall = new CinemaHall(reservations);
        Set<Seat> seats = repackSeatDtoToSeat(request.getReservation().getSeats());
        // TODO validacja na siedzenia
        Calculation calculation = calculatePrices(request);

        Customer customer = new Customer(request.getReservation().getCustomer().getFirstName(), request.getReservation().getCustomer().getLastName(),
                request.getReservation().getCustomer().getEmail(), request.getReservation().getCustomer().getPhone());
        Reservation reservation = new Reservation(show, calculation.getTickets(), seats, customer, calculation.getTotalPrice());
//        if (show.getReservations() == null || show.getReservations().isEmpty())
//            reservationRepository.save(reservation);
//        else
            reservationRepository.save(reservation);
        // validacja
        // pobierz show z rezerwacjami
        // tworzysz cinemaHall
        // validacja 2
        // siedzenia wolne czy zajęte
        // zrób rezerwację - zwracanie numeru
        // return reservation response(numer reserwacji)
        //

        return new CreateReservationResponse(reservation.getReservationNumber());
    }


    private Calculation calculatePrices(CreateReservationRequest request) {
        CalculatePriceRequest calculationPR = new CalculatePriceRequest();
        calculationPR.setShowId(request.getReservation().getShowId());
        calculationPR.setTickets(request.getReservation().getTickets());
        CalculatePriceResponse response = priceCalculator.calculatePrices(calculationPR);
        return response.getCalculation();
    }


    private Set<Seat> repackSeatDtoToSeat(Set<SeatDto> seatsDto) {
        Set<Seat> seats = new HashSet<>();
        for (SeatDto seatDto : seatsDto) {
            seats.add(new Seat(seatDto.getRow(), seatDto.getSeat()));
        }
        return seats;
    }


}
