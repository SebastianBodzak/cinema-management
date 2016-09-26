package pl.com.bottega.cinemamanagement.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemamanagement.api.AdminPanel;
import pl.com.bottega.cinemamanagement.api.requests.CreateMovieRequest;
import pl.com.bottega.cinemamanagement.api.requests.UpdatePriceRequest;

/**
 * Created by arkadiuszarak on 04/09/2016.
 */
@RestController
@RequestMapping("/movies")
public class MoviesController {

    private AdminPanel adminPanel;

    public MoviesController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PutMapping
    public void create(@RequestBody CreateMovieRequest createMovieRequest) {
        adminPanel.createMovie(createMovieRequest);
    }

    @PutMapping("/{movieId}/prices")
    public void updatePrices(@PathVariable Long movieId, @RequestBody UpdatePriceRequest updatePriceRequest) {
        updatePriceRequest.setMovieId(movieId);
        adminPanel.updatePrices(updatePriceRequest);
    }

}
