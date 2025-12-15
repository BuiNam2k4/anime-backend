package vn.kurisu.anime_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.kurisu.anime_service.entity.Anime;
@Repository
public interface AnimeReposiory extends JpaRepository<Anime, Long> {

    boolean existsByTitle(String title);
}
