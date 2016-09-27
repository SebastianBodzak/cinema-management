package pl.com.bottega.cinemamanagement.api.utils;

import java.util.regex.Pattern;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
public class PhoneValidator {

    private static final String PHONE_PATTERN_1 = "\\d{3}-\\d{3}-\\d{3}";
    private static final String PHONE_PATTERN_2 = "\\d{3}\\d{3}\\d{3}";

    private Pattern pattern;
    private Pattern pattern2;

    public PhoneValidator() {
        pattern = Pattern.compile(PHONE_PATTERN_1);
        pattern2 = Pattern.compile(PHONE_PATTERN_2);
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {
        return pattern.matcher(hex).matches() || pattern2.matcher(hex).matches();

    }
}
