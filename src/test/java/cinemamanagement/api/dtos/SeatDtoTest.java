package cinemamanagement.api.dtos;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.com.bottega.cinemamanagement.api.dtos.SeatDto;

/**
 * Created by Dell on 2016-09-28.
 */
@RunWith(JUnitParamsRunner.class)
public class SeatDtoTest {

    private SeatDto seatDto = new SeatDto();

    private static final Object[] getValidSeat() {
        return new Object[] {
                new Object[] {1, 15},
                new Object[] {10, 1}
        };
    }

    private static final Object[] getInvalidSeat() {
        return new Object[] {
                new Object[] {0, 1},
                new Object[] {1, 0},
                new Object[] {1, 16},
                new Object[] {11, 15},
                new Object[] {null, 15},
                new Object[] {1, null}
        };
    }

    @Test
    @Parameters(method = "getValidSeat")
    public void shouldCorrectValidateSeat(int row, int seat) {
        seatDto.setRow(row);
        seatDto.setSeat(seat);

        seatDto.validate();
    }

    @Test(expected = Exception.class)
    @Parameters(method = "getInvalidSeat")
    public void shouldThrowExceptionBecauseWrongParameters(int row, int seat) {
        seatDto.setRow(row);
        seatDto.setSeat(seat);

        seatDto.validate();
    }

}
