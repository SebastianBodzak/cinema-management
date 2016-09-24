package cinemamanagement.domain;

import com.google.common.collect.Sets;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.com.bottega.cinemamanagement.domain.Calculation;
import pl.com.bottega.cinemamanagement.domain.TicketOrder;
import pl.com.bottega.cinemamanagement.domain.TicketPrice;

import java.math.BigDecimal;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Dell on 2016-09-23.
 */
@RunWith(JUnitParamsRunner.class)
public class CalculationTest {

    private static final Object[] getSetsOfTicketsAndPrices() {
        return new Object[] {
            new Object[] {Sets.newHashSet(new TicketOrder("regular", 3), new TicketOrder("student", 2)),
                            Sets.newHashSet(new TicketPrice("regular", new BigDecimal(10)), new TicketPrice("student", new BigDecimal(8))),
                            new BigDecimal(46)},
            new Object[] {Sets.newHashSet(new TicketOrder("regular", 0), new TicketOrder("student", 2)),
                    Sets.newHashSet(new TicketPrice("regular", new BigDecimal(10)), new TicketPrice("student", new BigDecimal(8))),
                    new BigDecimal(16)},
            new Object[] {Sets.newHashSet(new TicketOrder("regular", 3), new TicketOrder("student", 2)),
                    Sets.newHashSet(new TicketPrice("school", new BigDecimal(100)), new TicketPrice("student", new BigDecimal(8)), new TicketPrice("regular", new BigDecimal(10))),
                    new BigDecimal(46)},
            new Object[] {Sets.newHashSet(new TicketOrder("regular", 3), new TicketOrder("student", 2), new TicketOrder("school", 2)),
                    Sets.newHashSet(new TicketPrice("school", new BigDecimal(100)), new TicketPrice("regular", new BigDecimal(10)), new TicketPrice("student", new BigDecimal(8))),
                    new BigDecimal(246)},
        };
    }

    @Test
    @Parameters(method = "getSetsOfTicketsAndPrices")
    public void shouldGetTicketPricesAndCalculateTotalPrice(Set<TicketOrder> tickets, Set<TicketPrice> prices, BigDecimal expectedResult) {
        Calculation calculation = new Calculation(tickets, prices);

        assertEquals(expectedResult, calculation.getTotalPrice());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotCalculateBecauseTicketDoesNotExist() {
        new Calculation(Sets.newHashSet(new TicketOrder("regular", 3), new TicketOrder("student", 2), new TicketOrder("does not exists", 3)),
                Sets.newHashSet(new TicketPrice("regular", new BigDecimal(10)), new TicketPrice("student", new BigDecimal(8))));
    }
}
