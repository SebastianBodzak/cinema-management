package pl.com.bottega.cinemamanagement.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bartosz on 2016-09-08.
 */
public class Hours {

    private String hour;

    public Hours(String hour) {
        this.hour = hour;
    }

    public String getHour() {
        return hour;
    }

    private String validateFormat(String format){
        //TODO
        return null;
    }
}
