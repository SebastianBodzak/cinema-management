package cinemamanagement.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.CalendarDto;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.ShowDto;
import java.util.*;

/**
 * Created by Dell on 2016-09-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowDtoTest {

    private ShowDto showDto;
    private Long movieId = 1L;
    private Collection<String> dates;

    @Mock
    private CalendarDto calendarDto;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        showDto = new ShowDto();
    }

//    @Test
    public void shouldNotVerifyBecauseMovieIdIsNull() {
        exception.expect(InvalidRequestException.class);

        exception.expectMessage("movieId with dates or with calendar are required");
    }

}
