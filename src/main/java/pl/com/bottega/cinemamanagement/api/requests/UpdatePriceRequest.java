package pl.com.bottega.cinemamanagement.api.requests;

import pl.com.bottega.cinemamanagement.api.InvalidRequestException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bartosz.paszkowski on 18.09.2016.
 */
public class UpdatePriceRequest {

    private Long movieId;
    private Map<String, BigDecimal> prices;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Map<String, BigDecimal> getPrices() {
        validate();
        return prices;
    }

    public void setPrices(HashMap<String, BigDecimal> prices) {
        this.prices = prices;
    }

    public void validate() {
        if (!(prices.containsKey("regular") && prices.containsKey("student"))) {
            throw new InvalidRequestException("Regular and student prices are required");
        }

    }
}
