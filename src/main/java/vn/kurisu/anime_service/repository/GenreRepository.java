package vn.kurisu.anime_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.kurisu.anime_service.entity.Genre;
@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

    boolean existsByName(String name);
}
