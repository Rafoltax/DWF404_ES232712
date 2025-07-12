package sv.edu.udb.repository.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter //metodo basico de getter
@Setter //metodo basico de setter
@Builder //diseño de ruta
@NoArgsConstructor //constructor default
@AllArgsConstructor //El atributo general de construccion


public class Movie {

    private Long id;
    private String name;
    private String type;
    private Integer releaseYear;

}
