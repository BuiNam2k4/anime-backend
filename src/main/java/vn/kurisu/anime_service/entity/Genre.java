package vn.kurisu.anime_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer malId;
    @ManyToMany(mappedBy = "genres")
    @ToString.Exclude
    @Builder.Default
    private Set<Anime> animes = new HashSet<>();


}
