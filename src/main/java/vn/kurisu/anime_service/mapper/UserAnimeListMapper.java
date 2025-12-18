package vn.kurisu.anime_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.kurisu.anime_service.dto.request.TrackingRequest;
import vn.kurisu.anime_service.dto.response.TrackingResponse;
import vn.kurisu.anime_service.entity.UserAnimeList;

@Mapper(componentModel = "spring")
public interface UserAnimeListMapper {
    @Mapping(target = "anime", ignore = true)
    @Mapping(target="user", ignore = true)
    @Mapping(target="id", ignore = true)
    @Mapping(target="updateAt", ignore = true)
    void updateTracking (TrackingRequest request, @MappingTarget UserAnimeList userAnimeList);
    @Mapping(target = "animeId", source = "anime.id")
    @Mapping(target = "animeTitle", source = "anime.title")
    @Mapping(target = "animeImageUrl", source = "anime.imageURL")
    @Mapping(target = "userId", source = "user.id")
    TrackingResponse toResponse(UserAnimeList entity);
}
