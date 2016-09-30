package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.api.dtos.CustomerReservationsDto;
import pl.com.bottega.cinemamanagement.api.dtos.SeatDto;
import pl.com.bottega.cinemamanagement.api.requests.CalculatePriceRequest;
import pl.com.bottega.cinemamanagement.api.requests.CreateReservationRequest;
import pl.com.bottega.cinemamanagement.api.responses.CalculatePriceResponse;
import pl.com.bottega.cinemamanagement.api.responses.CreateReservationResponse;
import pl.com.bottega.cinemamanagement.api.responses.ListSeatsResponse;
import pl.com.bottega.cinemamanagement.domain.*;
import pl.com.bottega.cinemamanagement.domain.repositories.ReservationRepository;
import pl.com.bottega.cinemamanagement.domain.repositories.ShowsRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */

@Service
public class ReservationManager {

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
        request.validate();
        Show show = showsRepository.findShowWithReservations(request.getShowId());
        if (show == null)
            throw new InvalidRequestException("Wrong show id");
        Set<Seat> seats = repackSeatDtoToSeat(request.getSeats());
        if (!new CinemaHall(show.getReservations()).checkIfSeatsCanBeReserved(seats))
            throw new InvalidRequestException("Seats can not be booked");
        Calculation calculation = calculatePrices(request);
        Customer customer = new Customer(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhone());
        Reservation reservation = new Reservation(show, calculation.getTickets(), seats, customer, calculation.getTotalPrice());
        reservationRepository.save(reservation);
        return new CreateReservationResponse(reservation.getReservationNumber());
    }

    @Transactional
    public CustomerReservationsDto findReservations(ReservationCriteria criteria) {
        criteria.verify();
        List<Reservation> reservation = reservationRepository.findActualReservations(criteria);
        if (reservation == null || reservation.isEmpty())
            throw new InvalidRequestException("There are no such reservations.");
        return new CustomerReservationsDto(reservation);
    }

    @Transactional
    public ListSeatsResponse listFreeAndOccupiedSeats(Long showId){
        Show show = showsRepository.findShowWithReservations(showId);
        CinemaHall cinemaHall = new CinemaHall(show.getReservations());
        Set<Seat> free = cinemaHall.getFreeSeats();
        Set<Seat> occupied = cinemaHall.getOccupiedSeats();

        Set<SeatDto> freeDto = packSeatToDto(free);
        Set<SeatDto> occupiedDto = packSeatToDto(occupied);

        return new ListSeatsResponse(freeDto, occupiedDto);
    }

    private Set<SeatDto> packSeatToDto(Set<Seat> seats){
        return seats.stream().map(e -> new SeatDto(e.getRow(), e.getNumber())).collect(Collectors.toSet());
    }

    private Calculation calculatePrices(CreateReservationRequest request) {
        CalculatePriceRequest calculationPR = new CalculatePriceRequest();
        calculationPR.setShowId(request.getShowId());
        calculationPR.setTickets(request.getTickets());
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
