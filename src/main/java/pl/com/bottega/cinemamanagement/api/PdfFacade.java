package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.Reservation;

import java.io.File;

/**
 * Created by ulvar on 01.10.2016.
 */
public interface PdfFacade {
    File createPdf(Reservation reservation);
}
