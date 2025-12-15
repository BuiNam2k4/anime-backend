package vn.kurisu.anime_service.entity;

import jakarta.persistence.*;
import lombok.*;
import vn.kurisu.anime_service.enums.WatchStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_anime_list")
public class UserAnimeList {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name= "anime_id")
    private Anime anime;

    @Enumerated(EnumType.STRING)
    private WatchStatus status;

    private Integer score;

    private Integer episodesWatched;

    private LocalDateTime updateAt;
}
