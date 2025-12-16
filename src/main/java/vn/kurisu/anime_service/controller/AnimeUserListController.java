package vn.kurisu.anime_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.kurisu.anime_service.dto.request.TrackingRequest;
import vn.kurisu.anime_service.dto.response.ApiResponse;
import vn.kurisu.anime_service.entity.UserAnimeList;
import vn.kurisu.anime_service.service.TrackingService;

@RestController
@RequestMapping("/api/tracking")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class AnimeUserListController {
    private TrackingService trackingService;
    @PostMapping
    public ApiResponse<UserAnimeList> updateList (@RequestBody TrackingRequest trackingRequest){
        return ApiResponse.<UserAnimeList>builder()
                .message("Them anime vao list thanh cong!")
                .result(trackingService.updateTracking(trackingRequest)).build();
    }
}
