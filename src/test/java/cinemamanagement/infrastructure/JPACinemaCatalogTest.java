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
import pl.com.bottega.cinemamanagement.api.CinemaCatalog;
import pl.com.bottega.cinemamanagement.api.responses.ListAllCinemasResponse;

import static org.junit.Assert.assertTrue;

/**
 * Created by Dell on 2016-09-28.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/application.xml")
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration
@Sql("/fixtures/cinemas.sql")
public class JPACinemaCatalogTest {

    @Autowired
    private CinemaCatalog jpaCinemaCatalog;

    @Sql("/fixtures/cinemas.sql")
    @Test
    @Transactional
    public void shouldLoadCinema() {
        ListAllCinemasResponse result = jpaCinemaCatalog.listAll();

        assertTrue(result.getCinemas().size() == 3);
    }

}
