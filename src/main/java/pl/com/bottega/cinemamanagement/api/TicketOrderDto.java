package pl.com.bottega.cinemamanagement.api;

/**
 * Created by Dell on 2016-09-19.
 */
public class TicketOrderDto {

    private String kind;
    private Integer count;

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
