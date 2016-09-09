package pl.com.bottega.cinemamanagement.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemamanagement.api.AdminPanel;
import pl.com.bottega.cinemamanagement.api.CinemaCatalog;
import pl.com.bottega.cinemamanagement.api.CreateCinemaRequest;
import pl.com.bottega.cinemamanagement.api.ListAllCinemasResponse;

/**
 * Created by Dell on 2016-09-04.
 */
@RestController
@RequestMapping("/cinemas")
public class CinemasController {

    private AdminPanel adminPanel;
    private CinemaCatalog cinemaCatalog;

    public CinemasController(AdminPanel adminPanel, CinemaCatalog cinemaCatalog) {

        this.adminPanel = adminPanel;
        this.cinemaCatalog = cinemaCatalog;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaRequest request) {
        adminPanel.createCinema(request);
    }

    public void addAll(ListAllCinemasResponse response) {

    }

    @GetMapping
    public ListAllCinemasResponse listAll(){
       return cinemaCatalog.listAll();
    }
}
