package pl.com.bottega.cinemamanagement.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemamanagement.api.AdminPanel;
import pl.com.bottega.cinemamanagement.api.CreateCinemaRequest;

/**
 * Created by Dell on 2016-09-04.
 */
@RestController
@RequestMapping("/cinemas")
public class CinemasController {

    private AdminPanel adminPanel;

    public CinemasController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaRequest request) {
        adminPanel.createCinema(request);
    }

    public void addAll(ListAllCinemaResponse response) {

    }

    public void listAll(ListAllCinemaResponse listAllCinemaResponse){

    }
}
