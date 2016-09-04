package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.CinemaRepository;

/**
 * Created by Dell on 2016-09-04.
 */
public class CreateCinemaRequest {

    private CinemaDto cinema;

    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
        this.cinema = cinema;
    }

    public void validate(CinemaRepository repository) {
        if (cinema == null)
            throw new InvalidRequestException("Cinema is required");
        cinema.validate();
    }

    public String getName() {
        return cinema.getName();
    }

    public String getCity() {
        return cinema.getCity();
    }

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

        public void validate() {
            if (name == null) {
                throw new InvalidRequestException("value NAME can not be empty");
            }

            if (city == null) {
                throw new InvalidRequestException("value CITY can not be empty");
            }


        }
    }
}




