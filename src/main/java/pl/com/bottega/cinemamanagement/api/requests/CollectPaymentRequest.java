package pl.com.bottega.cinemamanagement.api.requests;

import pl.com.bottega.cinemamanagement.api.dtos.PaymentDto;

/**
 * Created by arkadiuszarak on 01/10/2016.
 */
public class CollectPaymentRequest {
    private PaymentDto paymentDto;
    private Long reservationNumber;

    public PaymentDto getPaymentDto() {
        return paymentDto;
    }

    public void setPaymentDto(PaymentDto paymentDto) {
        this.paymentDto = paymentDto;
    }

    public Long getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(Long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public void validate(){}
}
