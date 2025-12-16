package vn.kurisu.anime_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.kurisu.anime_service.entity.UserAnimeList;

import java.util.Optional;

@Repository
public interface UserAnimeListRepository extends JpaRepository<UserAnimeList,Long> {
    Optional<UserAnimeList> findByUserIdAndAnimeId (Long userId, Long animeId);
}
