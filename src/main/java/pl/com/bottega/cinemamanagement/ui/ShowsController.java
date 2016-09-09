package pl.com.bottega.cinemamanagement.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemamanagement.api.AdminPanel;
import pl.com.bottega.cinemamanagement.api.CreateShowRequest;

/**
 * Created by Dell on 2016-09-08.
 */
@RestController
@RequestMapping("/cinemas")
public class ShowsController {

    private AdminPanel adminPanel;

    public ShowsController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PutMapping("/{cinemaId}/shows")
    public void create(@PathVariable Long cinemaId, @RequestBody CreateShowRequest request) {
        adminPanel.createShows(cinemaId, request);
    }
}
