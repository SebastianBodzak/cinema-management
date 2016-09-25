package pl.com.bottega.cinemamanagement.domain;

/**
 * Created by ulvar on 25.09.2016.
 */
public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public Customer(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
