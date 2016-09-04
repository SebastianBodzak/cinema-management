package pl.com.bottega.cinemamanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemamanagement.domain.Cinema;
import pl.com.bottega.cinemamanagement.domain.CinemaRepository;

/**
 * Created by Dell on 2016-09-04.
 */
@Service
public class AdminPanel {

    private CinemaRepository cinemaRepository;

    public AdminPanel(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Transactional
    public void createCinema(CreateCinemaRequest request) {
        Cinema cinema = new Cinema(request.getCinemaDto().getName(), request.getCinemaDto().getCity());
        cinemaRepository.save(cinema);
    }

    public void createMovie(CreateMovieRequest request) {
        //todo
    }

    public void createShow(CreateShowRequest request) {
        //todo
    }


}
