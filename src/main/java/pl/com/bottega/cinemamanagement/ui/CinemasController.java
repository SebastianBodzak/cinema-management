package pl.com.bottega.cinemamanagement.ui;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemamanagement.api.*;

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
                                                            @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate date){

        if (cinemaId == null || date == null)
            throw new InvalidRequestException("CinemaId and date are required"); //todo case that will not occur???

        ListMoviesInCinemaResponse response = movieCatalog.listMoviesInCinema(cinemaId, date);
        if (response.getMovies().isEmpty())
            throw new InvalidRequestException("At that date no movies have been found");
        else
            return response;
    }

}
