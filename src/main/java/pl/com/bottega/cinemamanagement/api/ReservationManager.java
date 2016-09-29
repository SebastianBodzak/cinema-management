package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.Set;

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

    public ReservationSearchResult find(ReservationCriteria criteria) {
        return reservationRepository.find(criteria);
    }

    @Transactional
    public ListSeatsResponse listFreeAndOccupiedSeats(Long showId){
        Show show = showsRepository.findShowWithReservations(showId);
        CinemaHall cinemaHall = new CinemaHall(show.getReservations());
        Set<Seat> free = cinemaHall.getFreeSeats();
        Set<Seat> occupied = cinemaHall.getOccupiedSeats();

        Set<SeatDto> freeDto = packSeatToDto(free);
        Set<SeatDto> occupiedDto = packSeatToDto(occupied);

        ListSeatsResponse.SeatsDto seatsDto = new ListSeatsResponse.SeatsDto(freeDto,occupiedDto);

//        return new ListSeatsResponse(seatsDto);
        ListSeatsResponse result = new ListSeatsResponse(seatsDto);
        System.out.println("free " + result.getSeats().getFree().size());
        System.out.println("occupied " + result.getSeats().getOccupied().size());

        return result;
    }

    private Set<SeatDto> packSeatToDto(Set<Seat> seat){
        Set<SeatDto> seatDto = new HashSet<>();
        for (Seat s : seat){
            seatDto.add(new SeatDto(s.getRow(), s.getNumber()));
        }
        return seatDto;
    }
}
