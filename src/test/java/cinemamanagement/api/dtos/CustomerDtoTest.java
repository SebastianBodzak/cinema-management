package cinemamanagement.api.dtos;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.dtos.CustomerDto;

/**
 * Created by Dell on 2016-09-28.
 */
public class CustomerDtoTest {

    private CustomerDto customerDto = new CustomerDto();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        customerDto.setFirstName("Janusz");
        customerDto.setLastName("Kowalski");
        customerDto.setEmail("mail@mail.com");
        customerDto.setPhone("123-456-789");
    }

    @Test
    public void shouldThrowExceptionBecauseEmptyFirstName() {
        exception.expect(InvalidRequestException.class);
        exception.expectMessage("First name can not be empty");
        customerDto.setFirstName("  ");

        customerDto.validate();
    }

    @Test
    public void shouldThrowExceptionBecauseEmptyLastName() {
        exception.expect(InvalidRequestException.class);
        exception.expectMessage("Last name can not be empty");
        customerDto.setLastName("  ");

        customerDto.validate();
    }

    @Test
    public void shouldThrowExceptionBecauseEmptyEmail() {
        exception.expect(InvalidRequestException.class);
        exception.expectMessage("Email can not be empty");
        customerDto.setEmail("  ");

        customerDto.validate();
    }
}
