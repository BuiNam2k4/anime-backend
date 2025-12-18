package vn.kurisu.anime_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.kurisu.anime_service.enums.WatchStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackingResponse {
    private Long animeId;
    private String animeTitle;
    private String animeImageUrl;

    // Không trả về User Object để cắt vòng lặp
    private Long userId;

    private WatchStatus status;
    private Integer score;
    private Integer episodesWatched;
    private String notes;
}
