package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.CinemaRepository;

/**
 * Created by Dell on 2016-09-04.
 */
public class CreateCinemaRequest {

    private CinemaDto cinemaDto;

    public class CinemaDto {

        private String name;
        private String city;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

    public CinemaDto getCinemaDto() {
        return cinemaDto;
    }

    public void setCinemaDto(CinemaDto cinemaDto) {
        this.cinemaDto = cinemaDto;
    }

    public void validate(CinemaRepository repository) {
        if (name == null) {
            throw new InvalidRequestException("value NAME can not be empty");
        }

        if (city == null) {
            throw new InvalidRequestException("value CITY can not be empty");
        }

    }

}




