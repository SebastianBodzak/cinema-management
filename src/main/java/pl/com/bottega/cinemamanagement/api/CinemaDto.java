package pl.com.bottega.cinemamanagement.api;

/**
 * Created by ulvar on 07.09.2016.
 */
public class CinemaDto {

    private Long id;
    private String name;
    private String city;

    public CinemaDto(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CinemaDto cinemaDto = (CinemaDto) o;

        if (!id.equals(cinemaDto.id)) return false;
        if (!name.equals(cinemaDto.name)) return false;
        return city.equals(cinemaDto.city);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }
}
