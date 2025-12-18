package vn.kurisu.anime_service.dto.request;

import lombok.Data;
import vn.kurisu.anime_service.enums.WatchStatus;

@Data
public class TrackingRequest {
   // private Long userId;
    private Long animeId;

    private WatchStatus status;
    private Integer score;
    private Integer episodesWatched;
    private String notes;
}
