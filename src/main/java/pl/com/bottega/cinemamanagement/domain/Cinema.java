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

    @Override
    public String toString() {
        return "Cinema{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
