package vn.kurisu.anime_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.kurisu.anime_service.dto.request.GenreRequest;
import vn.kurisu.anime_service.dto.response.GenreResponse;
import vn.kurisu.anime_service.entity.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target ="animes", ignore = true)
    Genre toGenre(GenreRequest genreRequest);
    GenreResponse toGenreResponse (Genre genre);
}
