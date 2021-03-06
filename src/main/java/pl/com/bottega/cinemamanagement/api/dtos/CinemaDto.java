package pl.com.bottega.cinemamanagement.api.dtos;

import pl.com.bottega.cinemamanagement.api.InvalidRequestException;

/**
 * Created by ulvar on 07.09.2016.
 */
public class CinemaDto {

    private Long id;
    private String name;
    private String city;

    public CinemaDto() {
    }

    public CinemaDto(Long id, String city, String name) {
        this.id = id;
        this.city = city;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void validate() throws InvalidRequestException {
        if (name == null || name.trim().isEmpty())
            throw new InvalidRequestException("value NAME can not be empty");

        if (city == null || city.trim().isEmpty())
            throw new InvalidRequestException("value CITY can not be empty");
    }
}


