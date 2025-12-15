package vn.kurisu.anime_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.kurisu.anime_service.dto.request.GenreRequest;
import vn.kurisu.anime_service.dto.response.ApiResponse;
import vn.kurisu.anime_service.dto.response.GenreResponse;
import vn.kurisu.anime_service.service.GenreService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genre")
@FieldDefaults(makeFinal = true)
public class GenreController {
    private GenreService genreService;

    @PostMapping
    public ApiResponse<GenreResponse> createGenre(@RequestBody GenreRequest genreRequest){
        return ApiResponse.<GenreResponse>builder()
                .result(genreService.createGenre(genreRequest))
                .build();
    }
}
