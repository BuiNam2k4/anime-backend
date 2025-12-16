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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name= "anime_id")
    private Anime anime;

    @Enumerated(EnumType.STRING) // Luu chu vao db
    private WatchStatus status;

    private Integer score;

    private Integer episodesWatched;

    private String notes;

    private LocalDateTime updateAt;

    @PrePersist
    @PreUpdate
    public void prePersist(){
        this.updateAt = LocalDateTime.now();
    }
}
