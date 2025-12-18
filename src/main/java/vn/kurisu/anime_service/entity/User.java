package vn.kurisu.anime_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String password; // Nhớ mã hóa BCrypt
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<UserAnimeList> myAnimeList = new ArrayList<>();
    @ElementCollection
    private Set<String> roles;
}

