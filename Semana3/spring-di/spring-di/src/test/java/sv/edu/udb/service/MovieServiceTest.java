package sv.edu.udb.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sv.edu.udb.repository.domain.Movie;
import sv.edu.udb.service.implementation.MovieServiceImpl;

import java.time.Duration;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
//COMPLEMENTARIOS
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieServiceImpl movieService;

    @Test
    void shouldMovieRepositoryNotNul_When_DIWorks(){
        assertNotNull(movieService.getMovieRepository());
    }

    @Test
    void shouldGetAMovie_When_TheMovieIdExists(){
        final Long expectedMovieId = 1L;
        final String expectedMovieName = "Inception";
        final Integer expectedReleaseYear = 2010;

        final Movie ActualMovie= movieService.findMovieById(expectedMovieId);

        assertEquals(ActualMovie.getId(), expectedMovieId);
        assertEquals(ActualMovie.getName(), expectedMovieName);
        assertEquals(ActualMovie.getReleaseYear(), expectedReleaseYear);
    }

    @Test
    void shouldThrowNoSuchElementException_WhenMovieIdDoesNotExists(){
        final Long fakeId = 4L;
        final String expectedErrorMessage = "La pelicula no existe";
        final Exception exception = assertThrows(NoSuchElementException.class, ()-> movieService.findMovieById(fakeId));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    //COMPLEMENTARIO

    //Ver que una pelicula sea reciente con AssertTrue
    @Test
    void ShouldMovieBeRecent_When_ReleaseAfter200(){
        Movie movie= movieService.findMovieById(1L);
        assertTrue(movie.getReleaseYear() > 2000, "La pelicula no es reciente");
    }


    //Ver que si dos objetos son exactamente la misma instancia
    @Test
    void ShouldReturnNull_WhenMovieNotFoundAndHandledGracefuly(){
        Movie movie1= movieService.findMovieById(1L);
        Movie movie2= movieService.findMovieById(1L);
        assertSame(movie1, movie2);
    }

    @Test
    void ShouldCompleteFindMovieByIdQuickly(){

        assertTimeout(Duration.ofMillis(500), ()-> movieService.findMovieById(1L), "El metodo esta tardando mas de lo esperado");

    }
}
