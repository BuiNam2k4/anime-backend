package vn.kurisu.anime_service.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.kurisu.anime_service.entity.Genre;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimeRequest {
    private String title;


    private String synopsis;

    private Integer episodes;

    private String imageURL;

    private List<Long> genreIDs;

}
