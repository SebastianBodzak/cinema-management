package pl.com.bottega.cinemamanagement.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemamanagement.api.EmailFacade;
import pl.com.bottega.cinemamanagement.domain.Reservation;

/**
 * Created by ulvar on 01.10.2016.
 */
@Component
public class EmailFacadeImpl implements EmailFacade {

    @Override
    public void sendTickets(Reservation reservation) {

    }
}
