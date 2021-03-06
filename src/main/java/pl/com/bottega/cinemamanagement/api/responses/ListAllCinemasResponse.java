package pl.com.bottega.cinemamanagement.api.responses;

import pl.com.bottega.cinemamanagement.api.dtos.CinemaDto;

import java.util.List;

/**
 * Created by ulvar on 07.09.2016.
 */
public class ListAllCinemasResponse {

    private List<CinemaDto> cinemas;

    public ListAllCinemasResponse(List<CinemaDto> cinemas) {
        this.cinemas = cinemas;
    }

    public List<CinemaDto> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<CinemaDto> cinemas) {
        this.cinemas = cinemas;
    }
}
