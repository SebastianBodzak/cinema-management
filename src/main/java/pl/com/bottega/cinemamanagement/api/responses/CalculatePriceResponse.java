package pl.com.bottega.cinemamanagement.api.responses;

import pl.com.bottega.cinemamanagement.domain.Calculation;

/**
 * Created by Dell on 2016-09-19.
 */
public class CalculatePriceResponse {

    private Calculation calculation;

    public CalculatePriceResponse(Calculation calculation) {
        this.calculation = calculation;
    }

    public Calculation getCalculation() {
        return calculation;
    }

    public void setCalculation(Calculation calculation) {
        this.calculation = calculation;
    }
}
