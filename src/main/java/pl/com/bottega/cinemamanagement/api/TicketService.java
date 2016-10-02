package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import pl.com.bottega.cinemamanagement.domain.Reservation;
import pl.com.bottega.cinemamanagement.domain.repositories.ReservationRepository;

/**
 * Created by bartosz.paszkowski on 02.10.2016.
 */
@Service
public class TicketService {

    private ReservationRepository reservationRepository;
    private PdfFacade pdfFacade;

    public TicketService(ReservationRepository reservationRepository, PdfFacade pdfFacade) {
        this.reservationRepository = reservationRepository;
        this.pdfFacade = pdfFacade;
    }

    public String printTicket(Long reservationNumber){
        Reservation reservation = reservationRepository.findReservationByNumber(reservationNumber);
        return pdfFacade.createPdf(reservation);
    }


}
