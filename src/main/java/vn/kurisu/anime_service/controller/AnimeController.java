package vn.kurisu.anime_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.kurisu.anime_service.dto.request.AnimeRequest;
import vn.kurisu.anime_service.dto.request.AnimeUpdateRequest;
import vn.kurisu.anime_service.dto.response.AnimeResponse;
import vn.kurisu.anime_service.dto.response.ApiResponse;
import vn.kurisu.anime_service.dto.response.PageResponse;
import vn.kurisu.anime_service.service.AnimeService;

import java.util.List;

@RestController
@RequestMapping("/api/anime")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class AnimeController {
    private AnimeService animeService;

    @GetMapping
    ApiResponse<PageResponse<AnimeResponse>> getAllAnime (
            @RequestParam(value="page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "search", required = false) String search
    ){
        return ApiResponse.<PageResponse<AnimeResponse>>builder()
                .message("Get List anime success")
                .result(animeService.getAllAnime(page, size, search))
                .build();
    }
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    ApiResponse<AnimeResponse> createAnime (@RequestBody AnimeRequest animeRequest){
        return ApiResponse.<AnimeResponse>builder()
                .message("Create anime successfull")
                .result(animeService.createAnime(animeRequest))
                .build();
    }
    @PutMapping("/{id}")
    ApiResponse<AnimeResponse> updateAnime(@PathVariable Long id, @RequestBody AnimeUpdateRequest request){
        return ApiResponse.<AnimeResponse>builder()
                .message("Update success")
                .result(animeService.updateAnime(request,id))
                .build();
    }
    @DeleteMapping("/{id}")
    ApiResponse<String> deleteAnnime (@PathVariable Long id){
        animeService.deleteAnime(id);
        return ApiResponse.<String>builder().result("Anime has been delete ")
                .build();
    }


}
