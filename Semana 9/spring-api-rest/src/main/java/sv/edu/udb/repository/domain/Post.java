package sv.edu.udb.repository.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    private LocalDate postDate;

    // Lombok @Builder with @NoArgsConstructor requires an all-args constructor for builder - keep simple
    public Post(Long id, String title, LocalDate postDate) {
        this.id = id;
        this.title = title;
        this.postDate = postDate;
    }
}
