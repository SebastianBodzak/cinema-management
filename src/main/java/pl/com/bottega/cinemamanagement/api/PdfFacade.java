package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.Reservation;

/**
 * Created by ulvar on 01.10.2016.
 */
public interface PdfFacade {
    String createPdf(Reservation reservation);
}
