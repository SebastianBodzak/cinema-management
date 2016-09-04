package pl.com.bottega.cinemamanagement.domain;

/**
 * Created by bartosz.paszkowski on 04.09.2016.
 */
public class Cinema {

    private String name;
    private String city;

    public Cinema(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CinemaCinema{" +
                "city ='" + city + '\'' +
                ", name ='" + name + '\'' +
                '}';
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
}
