package pl.com.bottega.cinemamanagement.api.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
public class PhoneValidator {
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "\\d{3}-\\d{3}-\\d{3}";

    public PhoneValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}
