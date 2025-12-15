package vn.kurisu.anime_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import vn.kurisu.anime_service.dto.request.AnimeRequest;
import vn.kurisu.anime_service.dto.response.AnimeResponse;
import vn.kurisu.anime_service.dto.response.ApiResponse;
import vn.kurisu.anime_service.service.AnimeService;

import java.util.List;

@RestController
@RequestMapping("/api/anime")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class AnimeController {
    private AnimeService animeService;

    @GetMapping
    ApiResponse<List<AnimeResponse>> getAllAnime (){
        return ApiResponse.<List<AnimeResponse>>builder().result(animeService.getAllAnime()).build();
    }

    @PostMapping
    ApiResponse<AnimeResponse> createAnime (@RequestBody AnimeRequest animeRequest){
        return ApiResponse.<AnimeResponse>builder()
                .result(animeService.createAnime(animeRequest))
                .build();
    }

}
