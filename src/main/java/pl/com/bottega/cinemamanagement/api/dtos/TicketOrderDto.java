package pl.com.bottega.cinemamanagement.api.dtos;

import pl.com.bottega.cinemamanagement.api.InvalidRequestException;

/**
 * Created by Dell on 2016-09-19.
 */
public class TicketOrderDto {

    private String kind;
    private Integer count;

    public void validate() {
        if (kind == null || count == null)
            throw new InvalidRequestException("Missing kind or count");
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
