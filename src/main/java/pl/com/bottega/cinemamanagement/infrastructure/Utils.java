package pl.com.bottega.cinemamanagement.infrastructure;

import java.util.List;

/**
 * Created by Dell on 2016-09-27.
 */
public class Utils {

    public static <T> T returnSingleResult(List<T> result) {
        return result.isEmpty() ? null : result.get(0);
    }
}
