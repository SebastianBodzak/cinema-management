package pl.com.bottega.cinemamanagement.api;

import com.google.common.base.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketOrderDto that = (TicketOrderDto) o;
        return Objects.equal(kind, that.kind);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(kind);
    }
}
