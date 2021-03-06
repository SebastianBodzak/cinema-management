package cinemamanagement.api.requests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemamanagement.api.requests.CreateMovieRequest;
import pl.com.bottega.cinemamanagement.api.InvalidRequestException;
import pl.com.bottega.cinemamanagement.domain.repositories.MovieRepository;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

/**
 * Created by arkadiuszarak on 07/09/2016.*/


@RunWith(MockitoJUnitRunner.class)
public class CreateMovieRequestTest {

    private CreateMovieRequest createMovieRequest;
    private CreateMovieRequest.MovieDto movieDto;

    private String anyTitle = "any title";
    private String anyDescription = "any description";
    private Set<String> anyActors = new HashSet<String>(){{
        add("any actor 1");
        add("any actor 2");
        add("any actor 3");
    }};
    private Set<String> anyGenres = new HashSet<String>(){{
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
        exception.expectMessage("value TITLE can not be empty");

        movieDto.validate();
    }

    @Test
    public void shouldNotCreateMovieBecauseTitleEmpty() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(emptyString, anyDescription, anyActors, anyGenres, anyMinAge, anyLength);
        exception.expectMessage("value TITLE can not be empty");

        movieDto.validate();
    }

    @Test
    public void shouldNotCreateMovieBecauseDescriptionNull() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(anyTitle, null, anyActors, anyGenres, anyMinAge, anyLength);
        exception.expectMessage("value DESCRIPTION can not be empty");

        movieDto.validate();
    }

    @Test
    public void shouldNotCreateMovieBecauseDescriptionEmpty() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(anyTitle, emptyString, anyActors, anyGenres, anyMinAge, anyLength);
        exception.expectMessage("value DESCRIPTION can not be empty");

        movieDto.validate();
    }

    @Test
    public void shouldNotCreateMovieBecauseActorsNull() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(anyTitle, anyDescription, null, anyGenres, anyMinAge, anyLength);
        exception.expectMessage("value ACTORS can not be empty");

        movieDto.validate();
    }

    @Test
    public void shouldNotCreateMovieBecauseGenresNull() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(anyTitle, anyDescription, anyActors, null, anyMinAge, anyLength);
        exception.expectMessage("value GENRES can not be empty");

        movieDto.validate();
    }

    @Test
    public void shouldNotCreateMovieBecauseMinAgeIsNull() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(anyTitle, anyDescription, anyActors, anyGenres, null, anyLength);
        exception.expectMessage("value MIN AGE can not be empty");

        movieDto.validate();
    }

    @Test
    public void shouldNotCreateMovieBecauseLengthIsNull() throws InvalidRequestException{
        exception.expect(InvalidRequestException.class);
        createMovieRequestInstance(anyTitle, anyDescription, anyActors, anyGenres, anyMinAge, null);
        exception.expectMessage("value LENGTH can not be empty");

        movieDto.validate();
    }

    private void createMovieRequestInstance(String title, String description, Set<String> actors, Set<String> genres, Integer minAge, Integer length){
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
