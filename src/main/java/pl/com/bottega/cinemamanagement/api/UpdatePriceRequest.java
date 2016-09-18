package pl.com.bottega.cinemamanagement.api;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by bartosz.paszkowski on 18.09.2016.
 */
public class UpdatePriceRequest {

    private Long movieId;
    private HashMap<String,BigDecimal> prices;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public HashMap getPrices() {
        return prices;
    }

    public void setPrices(HashMap<String,BigDecimal> prices) {
        this.prices = prices;
    }
}
