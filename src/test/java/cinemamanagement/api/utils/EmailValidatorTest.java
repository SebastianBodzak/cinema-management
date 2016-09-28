package cinemamanagement.api.utils;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.com.bottega.cinemamanagement.api.utils.EmailValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dell on 2016-09-28.
 */
@RunWith(JUnitParamsRunner.class)
public class EmailValidatorTest {

    private EmailValidator emailValidator = new EmailValidator();

    private static final Object[] getValidEMail() {
        return new Object[] {
                new Object[] {"sj@gmail.com"},
                new Object[] {"sj@gl.pl"},
                new Object[] {"sj@gl.anotherAbbreviation"}
        };
    }

    private static final Object[] getInvalidEmail() {
        return new Object[] {
                new Object[] {"sfjkjfsjkfsjfsk"},
                new Object[] {"sfjkjfsjkfsjfsk@"},
                new Object[] {"@"},
                new Object[] {"@gmail.com"},
                new Object[] {"sad@gmail"}
        };
    }

    @Test
    @Parameters(method = "getValidEMail")
    public void shouldReturnTrueResultBecauseOfValidMail(String validMail) {
        boolean result = emailValidator.validate(validMail);

        assertTrue(result);
    }

    @Test
    @Parameters(method = "getInvalidEmail")
    public void shouldReturnFalseResultBecauseOfInvalidMail(String invalidMail) {
        boolean result = emailValidator.validate(invalidMail);

        assertFalse(result);
    }
}
