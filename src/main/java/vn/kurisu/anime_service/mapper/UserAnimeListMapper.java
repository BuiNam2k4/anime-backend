package vn.kurisu.anime_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.kurisu.anime_service.dto.request.TrackingRequest;
import vn.kurisu.anime_service.entity.UserAnimeList;

@Mapper(componentModel = "spring")
public interface UserAnimeListMapper {
    @Mapping(target = "anime", ignore = true)
    @Mapping(target="user", ignore = true)
    @Mapping(target="id", ignore = true)
    @Mapping(target="updatedAt", ignore = true)
    void updateTracking (TrackingRequest request, @MappingTarget UserAnimeList userAnimeList);
}
