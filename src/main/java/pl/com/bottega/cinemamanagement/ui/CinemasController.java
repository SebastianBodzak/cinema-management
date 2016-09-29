package pl.com.bottega.cinemamanagement.ui;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemamanagement.api.*;
import pl.com.bottega.cinemamanagement.api.requests.CreateCinemaRequest;
import pl.com.bottega.cinemamanagement.api.requests.CreateShowRequest;
import pl.com.bottega.cinemamanagement.api.responses.ListAllCinemasResponse;
import pl.com.bottega.cinemamanagement.api.responses.ListMoviesInCinemaResponse;

import java.time.LocalDate;

/**
 * Created by Dell on 2016-09-04.
 */
@RestController
@RequestMapping("/cinemas")
public class CinemasController {

    private AdminPanel adminPanel;
    private CinemaCatalog cinemaCatalog;
    private MovieCatalog movieCatalog;

    public CinemasController(AdminPanel adminPanel, CinemaCatalog cinemaCatalog, MovieCatalog movieCatalog) {

        this.adminPanel = adminPanel;
        this.cinemaCatalog = cinemaCatalog;
        this.movieCatalog = movieCatalog;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaRequest request) {
        adminPanel.createCinema(request);
    }

    @GetMapping
    public ListAllCinemasResponse listAll() {

        return cinemaCatalog.listAll();
    }

    @GetMapping("/{cinemaId}/movies")
    public ListMoviesInCinemaResponse listMoviesInCinema(@PathVariable Long cinemaId, @RequestParam
    @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate date) {

        return movieCatalog.listMoviesInCinema(cinemaId, date);
    }

    @PutMapping("/{cinemaId}/shows")
    public void create(@PathVariable Long cinemaId, @RequestBody CreateShowRequest request) {
        request.setCinemaId(cinemaId);
        adminPanel.createShows(request);
    }

}
