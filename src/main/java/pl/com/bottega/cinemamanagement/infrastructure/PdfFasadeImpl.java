package pl.com.bottega.cinemamanagement.infrastructure;

import pl.com.bottega.cinemamanagement.api.PdfFasade;
import pl.com.bottega.cinemamanagement.domain.Reservation;

import java.io.File;

/**
 * Created by ulvar on 01.10.2016.
 */
public class PdfFasadeImpl implements PdfFasade {
    @Override
    public File createPdf(Reservation reservation) {
        return null;
    }
}
