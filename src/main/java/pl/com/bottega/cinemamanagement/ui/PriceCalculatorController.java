package pl.com.bottega.cinemamanagement.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemamanagement.api.AdminPanel;
import pl.com.bottega.cinemamanagement.api.CalculatePriceRequest;
import pl.com.bottega.cinemamanagement.api.CalculatePriceResponse;

/**
 * Created by Dell on 2016-09-19.
 */
@RestController
@RequestMapping("/price_calculator")
public class PriceCalculatorController {

    private AdminPanel adminPanel;

    public PriceCalculatorController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PostMapping
    public CalculatePriceResponse calculatePrice(CalculatePriceRequest request) {
        return adminPanel.calculatePrice(request);
    }
}
