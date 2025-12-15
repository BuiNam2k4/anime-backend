package vn.kurisu.anime_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.kurisu.anime_service.dto.request.AnimeRequest;
import vn.kurisu.anime_service.dto.response.AnimeResponse;
import vn.kurisu.anime_service.entity.Anime;
import vn.kurisu.anime_service.entity.Genre;
import vn.kurisu.anime_service.repository.AnimeReposiory;

@Mapper(componentModel = "spring")
public interface AnimeMapper {
    @Mapping(target = "genres", ignore = true)
    Anime toAnime (AnimeRequest animeRequest);
    @Mapping(target = "genreNames", source = "genres")
    AnimeResponse toAnimeResponse (Anime anime);
    default String mapGenreToString(Genre genre) {
        if (genre == null) {
            return null;
        }
        return genre.getName(); // Giả sử trong Genre bạn đã có field 'name'
    }


}
