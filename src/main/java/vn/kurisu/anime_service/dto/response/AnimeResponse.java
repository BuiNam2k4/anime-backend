package vn.kurisu.anime_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimeResponse {
    private String title;

    private String synopsis;

    private Integer episodes;

    private String imageURL;

    Set<String> genreNames;

}
