package pl.com.bottega.cinemamanagement.infrastructure;

import org.springframework.stereotype.Service;
import pl.com.bottega.cinemamanagement.api.PdfFacade;
import pl.com.bottega.cinemamanagement.domain.Reservation;

import java.io.File;

/**
 * Created by ulvar on 01.10.2016.
 */
@Service
public class PdfFacadeImpl implements PdfFacade {

    @Override
    public File createPdf(Reservation reservation) {
        return null;
    }
}