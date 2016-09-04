package pl.com.bottega.cinemamanagement.ui;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by ulvar on 04.09.2016.
 */
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler//(AuthRequiredException.class)
    public ResponseEntity<String> handleInvalidRequestException() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<String>(
                "{'error': 'Value can not be empty?'}", //TODO
                headers,
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

}



