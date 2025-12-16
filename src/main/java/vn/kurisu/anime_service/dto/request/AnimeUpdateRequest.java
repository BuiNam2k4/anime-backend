package vn.kurisu.anime_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimeUpdateRequest {
    private String title;

    private String synopsis;

    private Integer episodes;

    private String imageURL;

    private List<Long> genreIDs;

}
