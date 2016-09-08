package cinemamanagement.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.CreateMovieRequest;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.domain.MovieRepository;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;

/**
 * Created by arkadiuszarak on 07/09/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateMovieRequestTest {

    private CreateMovieRequest createMovieRequest;
    private CreateMovieRequest.MovieDto movieDto;

    private String anyTitle = "any title";
    private String anyDescription = "any description";
    private Collection<String> anyActors = new ArrayList<String>(){{
        add("any actor 1");
        add("any actor 2");
        add("any actor 3");
    }};
    private Collection<String> anyGenres = new ArrayList<String>(){{
        add("any genres1");
        add("any genres2");
    }};
    private int anyMinAge = 16;
    private int anyLength = 120;

    private String emptyString = "";

    @Mock
    private MovieRepository movieRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        createMovieRequest = new CreateMovieRequest();
    }

    @Test
    public void shouldValidateMovie(){
        createMovieRequestInstance(anyTitle, anyDescription, anyActors, anyGenres, anyMinAge, anyLength);

        createMovieRequest.validate();

        assertEquals(anyTitle, movieDto.getTitle());
    }

    @Test
    public void shouldNotCreateMovieBecauseTitleNull() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(null, anyDescription, anyActors, anyGenres, anyMinAge, anyLength);

        movieDto.validate();

        exception.expectMessage("value TITLE can not be empty");
    }

    @Test
    public void shouldNotCreateMovieBecauseTitleEmpty() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(emptyString, anyDescription, anyActors, anyGenres, anyMinAge, anyLength);

        movieDto.validate();

        exception.expectMessage("value TITLE can not be empty");
    }

    @Test
    public void shouldNotCreateMovieBecauseDescriptionNull() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(anyTitle, null, anyActors, anyGenres, anyMinAge, anyLength);

        movieDto.validate();

        exception.expectMessage("value DESCRIPTION can not be empty");
    }

    @Test
    public void shouldNotCreateMovieBecauseDescriptionEmpty() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(anyTitle, emptyString, anyActors, anyGenres, anyMinAge, anyLength);

        movieDto.validate();

        exception.expectMessage("value DESCRIPTION can not be empty");
    }

    @Test
    public void shouldNotCreateMovieBecauseActorsNull() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(anyTitle, anyDescription, null, anyGenres, anyMinAge, anyLength);

        movieDto.validate();

        exception.expectMessage("value ACTORS can not be empty");
    }

    @Test
    public void shouldNotCreateMovieBecauseGenresNull() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(anyTitle, anyDescription, anyActors, null, anyMinAge, anyLength);

        movieDto.validate();

        exception.expectMessage("value GENRES can not be empty");
    }

    @Test
    public void shouldNotCreateMovieBecauseMinAgeZero() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(anyTitle, anyDescription, anyActors, anyGenres, 0, anyLength);

        movieDto.validate();

        exception.expectMessage("value MIN AGE can not be empty");
    }

    @Test
    public void shouldNotCreateMovieBecauseLengthZero() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(anyTitle, anyDescription, anyActors, anyGenres, anyMinAge, 0);

        movieDto.validate();

        exception.expectMessage("value LENGTH can not be empty");
    }

    private void createMovieRequestInstance(String title, String description, Collection<String> actors, Collection<String> genres,Integer minAge, Integer length){
        movieDto = createMovieRequest.new MovieDto();
        createMovieRequest.setMovie(movieDto);
        movieDto.setTitle(title);
        movieDto.setDescription(description);
        movieDto.setActors(actors);
        movieDto.setGeners(genres);
        movieDto.setMinAge(minAge);
        movieDto.setLenght(length);
    }

}
