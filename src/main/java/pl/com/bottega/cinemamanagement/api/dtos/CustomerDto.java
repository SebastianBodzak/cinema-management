package pl.com.bottega.cinemamanagement.api.dtos;

import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.utils.EmailValidator;
import pl.com.bottega.cinemamanagement.api.utils.PhoneValidator;

/**
 * Created by bartosz.paszkowski on 25.09.2016.
 */
public class CustomerDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private EmailValidator emailValidator = new EmailValidator();
    private PhoneValidator phoneValidator = new PhoneValidator();

    public void validate() {
        if (firstName == null || firstName.trim().isEmpty())
            throw new InvalidRequestException("First name can not be empty");
        if (lastName == null || lastName.trim().isEmpty())
            throw new InvalidRequestException("Last name can not be empty");
        if (email == null || email.trim().isEmpty())
            throw new InvalidRequestException("Email can not be empty");
        if (!emailValidator.validate(email))
            throw new InvalidRequestException("Wrong email");
        if (!phoneValidator.validate(phone))
            throw new InvalidRequestException("Wrong phone number. Write in format XXX-XXX-XXX");

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
