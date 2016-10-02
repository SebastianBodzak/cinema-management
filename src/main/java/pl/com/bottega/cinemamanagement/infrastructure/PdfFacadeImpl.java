package pl.com.bottega.cinemamanagement.infrastructure;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import pl.com.bottega.cinemamanagement.api.PdfFacade;
import pl.com.bottega.cinemamanagement.domain.Reservation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by ulvar on 01.10.2016.
 */
@Component
public class PdfFacadeImpl implements PdfFacade {

    //public static final String DIRECTORY = "C:\\ticketPdf\\";
    public static final String DIRECTORY = System.getProperty("user.home") + File.separator;

    @Override
    public String createPdf(Reservation reservation) {
        Document document = new Document();
        String fileName = reservation.getReservationNumber() + ".pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(DIRECTORY + fileName));
            document.open();
            document.add(new Chunk(reservation.getShow().getMovie().getTitle()));
            document.add(new Chunk(reservation.toString()));
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fileName;
    }

}