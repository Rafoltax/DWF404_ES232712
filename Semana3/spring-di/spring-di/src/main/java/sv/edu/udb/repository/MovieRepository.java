package sv.edu.udb.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import sv.edu.udb.repository.domain.Movie;

import java.util.List;
import java.util.NoSuchElementException;

//esto simula un banco de datos dentro de un archivo de texto ya sea un excel, JSON o xml

@Component
public class MovieRepository {

    //lista de peliculas en memoria
    private List<Movie>listOfMovies;

    @PostConstruct
    private void init() {
        final Movie movie_1= Movie
                .builder()
                .id(1L)
                .name("Inception")
                .type("Science Fiction")
                .releaseYear(2010)
                .build();
        final Movie movie_2= Movie
                .builder()
                .id(1L)
                .name("Jurassic World")
                .type("Science Fiction")
                .releaseYear(2015)
                .build();
        final Movie movie_3= Movie
                .builder()
                .id(1L)
                .name("Interstellar")
                .type("Science Fiction")
                .releaseYear(2014)
                .build();
        this.listOfMovies = List.of(movie_1,movie_2,movie_3);
    }
    //metodo de busqueda de peli por ID

    public Movie findMovieById(final Long id){
        return this.listOfMovies
                .stream()
                .filter(movie->id.equals(movie.getId()))
                .findFirst()
                .orElseThrow
                        (()-> new NoSuchElementException("La pelicula no existe"));
    }

}
