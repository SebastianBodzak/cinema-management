package cinemamanagement.api.utils;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.com.bottega.cinemamanagement.api.utils.PhoneValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dell on 2016-09-28.
 */
@RunWith(JUnitParamsRunner.class)
public class PhoneValidatorTest {

    private PhoneValidator phoneValidator = new PhoneValidator();

    private static final Object[] getValidPhone() {
        return new Object[] {
                new Object[] {"444-555-333"},
                new Object[] {"444555333"},
                new Object[] {"024-404-301"},
                new Object[] {"024404301"}
        };
    }

    private static final Object[] getInvalidPhone() {
        return new Object[] {
                new Object[] {""},
                new Object[] {"afa"},
                new Object[] {"646-444434"},
                new Object[] {"646-444434"},
                new Object[] {"646-4a4-434"},
                new Object[] {"646-433-4343"},
                new Object[] {"6464a4434"},
                new Object[] {"64644434"},
                new Object[] {"6464443435453545355"}
        };
    }

    @Test
    @Parameters(method = "getValidPhone")
    public void shouldValidatePhoneAndReturnTrueResponse(String validPhone) {
        boolean result = phoneValidator.validate(validPhone);

        assertTrue(result);
    }

    @Test
    @Parameters(method = "getInvalidPhone")
    public void shouldReturnFalseResponse(String invalidPhone) {
        boolean result = phoneValidator.validate(invalidPhone);

        assertFalse(result);
    }
}
