package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.domain.ReservationStatus;

/**
 * Created by Bartosz on 2016-09-27.
 */
public class ReservationCriteria {
    private String lastName;
    private ReservationStatus status;

    public void verify() {
        if (lastName == null || lastName.trim().isEmpty())
            throw new InvalidRequestException("Last name is required");
        if (status == null || status.toString().trim().isEmpty())
            throw new InvalidRequestException("Status is required");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public boolean isLastNameDefined() {
        return lastName != null;
    }

    public boolean isStatusDefined() {
        return status != null;
    }
}
