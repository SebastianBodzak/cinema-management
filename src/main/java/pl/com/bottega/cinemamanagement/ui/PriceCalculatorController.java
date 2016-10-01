package pl.com.bottega.cinemamanagement.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemamanagement.api.PriceCalculator;
import pl.com.bottega.cinemamanagement.api.requests.CalculatePriceRequest;
import pl.com.bottega.cinemamanagement.api.responses.CalculatePriceResponse;

/**
 * Created by Dell on 2016-09-19.
 */
@RestController
@RequestMapping("/price_calculator")
public class PriceCalculatorController {

    private PriceCalculator priceCalculator;

    public PriceCalculatorController(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @PostMapping
    public CalculatePriceResponse calculatePrice(@RequestBody CalculatePriceRequest request) {
        return priceCalculator.calculatePrices(request);
    }
}
