package vn.kurisu.anime_service.mapper;

import org.mapstruct.Mapper;
import vn.kurisu.anime_service.dto.request.RegisterRequest;
import vn.kurisu.anime_service.dto.response.UserResponse;
import vn.kurisu.anime_service.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser (RegisterRequest request);
    UserResponse toUserResponse (User user);
}
