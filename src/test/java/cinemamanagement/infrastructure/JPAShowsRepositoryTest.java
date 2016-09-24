package cinemamanagement.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.domain.TicketPrice;
import pl.com.bottega.cinemamanagement.infrastructure.JPAShowsRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dell on 2016-09-23.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/application.xml")
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
@Sql("/fixtures/ticketprices.sql")
public class JPAShowsRepositoryTest {

    @Autowired
    private JPAShowsRepository showsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private LocalDate testDate = LocalDate.of(2016, 9 ,20);
    private Long testCienmaId = 2L;
    private Long showId = 12L;

    @Sql("/fixtures/ticketprices.sql")
    @Test
    @Transactional
    public void shouldlistMoviesInCinema(){
        Set<TicketPrice> prices = showsRepository.listTicketPrices(showId);

        assertTrue(prices.size() == 2);
    }
}
