package pl.com.bottega.cinemamanagement.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemamanagement.api.AdminPanel;
import pl.com.bottega.cinemamanagement.api.CreateMovieRequest;

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
}
