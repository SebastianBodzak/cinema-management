package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.Calculation;

/**
 * Created by Dell on 2016-09-19.
 */
public class CalculatePriceResponse {

    private Calculation calculation;

    public Calculation getCalculation() {
        return calculation;
    }

    public void setCalculation(Calculation calculation) {
        this.calculation = calculation;
    }
}
