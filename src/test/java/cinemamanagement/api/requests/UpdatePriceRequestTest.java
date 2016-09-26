package cinemamanagement.api.requests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.api.requests.UpdatePriceRequest;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by Bartosz on 2016-09-22.
 */
public class UpdatePriceRequestTest {

    private UpdatePriceRequest updatePriceRequest;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldValidate(){
        updatePriceRequest = createUpdatePriceRequest();

        updatePriceRequest.validate();
    }


    @Test
    public void shouldNotValidate(){
        exception.expect(InvalidRequestException.class);
        updatePriceRequest = createUpdatePriceRequestWithoutStudent();
        exception.expectMessage("Regular and student prices are required");

        updatePriceRequest.validate();

    }
    private UpdatePriceRequest createUpdatePriceRequest() {
        UpdatePriceRequest updatePriceRequest = new UpdatePriceRequest();
        updatePriceRequest.setMovieId(0L);
        HashMap<String,BigDecimal> map = new HashMap<>();
        map.put("regular",new BigDecimal(15.00));
        map.put("student",new BigDecimal(10.00));
        map.put("school",new BigDecimal(8.00));
        map.put("children",new BigDecimal(5.00));
        updatePriceRequest.setPrices(map);
        return updatePriceRequest;
    }

    private UpdatePriceRequest createUpdatePriceRequestWithoutStudent() {
        UpdatePriceRequest updatePriceRequest = new UpdatePriceRequest();
        updatePriceRequest.setMovieId(0L);
        HashMap<String,BigDecimal> map = new HashMap<>();
        map.put("regular",new BigDecimal(15.00));
        map.put("school",new BigDecimal(8.00));
        map.put("children",new BigDecimal(5.00));
        updatePriceRequest.setPrices(map);
        return updatePriceRequest;
    }
}
