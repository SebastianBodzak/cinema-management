package pl.com.bottega.cinemamanagement.api;

import pl.com.bottega.cinemamanagement.api.responses.ListAllCinemasResponse;

/**
 * Created by ulvar on 07.09.2016.
 */
public interface CinemaCatalog {

    ListAllCinemasResponse listAll();
}
