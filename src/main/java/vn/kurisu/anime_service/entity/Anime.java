package vn.kurisu.anime_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "animes")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String synopsis;

    private Integer episodes;

    private String imageURL;

    @ManyToMany
    @JoinTable(
            name = "anime_genres",
            joinColumns= @JoinColumn(name="anime_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id")
    )
    private Set<Genre> genres = new HashSet<>();
}
