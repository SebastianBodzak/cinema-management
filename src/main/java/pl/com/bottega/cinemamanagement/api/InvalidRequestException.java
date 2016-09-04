package pl.com.bottega.cinemamanagement.api;

/**
 * Created by ulvar on 04.09.2016.
 */
public class InvalidRequestException extends RuntimeException {


    public InvalidRequestException(String message) {
        super(message);
    }


}
