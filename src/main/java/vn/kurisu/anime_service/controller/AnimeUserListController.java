package vn.kurisu.anime_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.kurisu.anime_service.dto.request.TrackingRequest;
import vn.kurisu.anime_service.dto.response.ApiResponse;
import vn.kurisu.anime_service.dto.response.TrackingResponse;
import vn.kurisu.anime_service.entity.UserAnimeList;
import vn.kurisu.anime_service.service.TrackingService;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class AnimeUserListController {
    private TrackingService trackingService;
    @PostMapping
    public ApiResponse<TrackingResponse> updateList (@RequestBody TrackingRequest trackingRequest){
        return ApiResponse.<TrackingResponse>builder()
                .message("Them anime vao list thanh cong!")
                .result(trackingService.updateTracking(trackingRequest))
                .build();

    }
    @GetMapping("/my-animelist")
    public ApiResponse<List<TrackingResponse>> getMyList (){
        return ApiResponse.<List<TrackingResponse>>builder()
                .message("Lay danh sach thanh cong")
                .result(trackingService.getMyTrackingList())
                .build();
    }

}
