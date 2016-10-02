package cinemamanagement.infrastructure;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.com.bottega.cinemamanagement.api.EmailFacade;
import pl.com.bottega.cinemamanagement.domain.Reservation;
import pl.com.bottega.cinemamanagement.infrastructure.EmailFacadeImpl;

/**
 * Created by ulvar on 02.10.2016.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/application.xml")
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
public class EmailFacadeImlpTest {

    @Autowired
    private EmailFacadeImpl emailFacadeImpl;


    @Before
    public void setup(){
//        emailFacadeImpl = new EmailFacadeImpl();
    }
    @Test
    public void shouldSendEmail() {
    emailFacadeImpl.sendTickets(new Reservation());
    }
}
