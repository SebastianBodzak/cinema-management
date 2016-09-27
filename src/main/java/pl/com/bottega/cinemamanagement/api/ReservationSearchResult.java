package pl.com.bottega.cinemamanagement.api;

import java.util.List;

/**
 * Created by Bartosz on 2016-09-27.
 */
public class ReservationSearchResult {

    private List<ReservationFindDto> resultList;

    public ReservationSearchResult(List<ReservationFindDto> resultList) {
        this.resultList = resultList;
    }

    public Iterable<ReservationFindDto> getResultList() {
        return resultList;
    }

    public void setResultList(List<ReservationFindDto> resultList) {
        this.resultList = resultList;
    }
}
