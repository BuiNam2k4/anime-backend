package vn.kurisu.anime_service.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.kurisu.anime_service.dto.request.TrackingRequest;
import vn.kurisu.anime_service.entity.Anime;
import vn.kurisu.anime_service.entity.User;
import vn.kurisu.anime_service.entity.UserAnimeList;
import vn.kurisu.anime_service.exception.AppException;
import vn.kurisu.anime_service.exception.ErrorCode;
import vn.kurisu.anime_service.mapper.UserAnimeListMapper;
import vn.kurisu.anime_service.repository.AnimeReposiory;
import vn.kurisu.anime_service.repository.UserAnimeListRepository;
import vn.kurisu.anime_service.repository.UserRepository;

@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
@Service
public class TrackingService {
    private UserAnimeListRepository userAnimeListRepository;
    private AnimeReposiory animeReposiory;
    private UserRepository userRepository;
    private UserAnimeListMapper trackingMapper;


    public UserAnimeList updateTracking(TrackingRequest trackingRequest){
        User user = userRepository.findById(trackingRequest.getUserId())
                .orElseThrow(()-> new AppException(ErrorCode.NOT_FOUND));
        Anime anime = animeReposiory.findById(trackingRequest.getAnimeId())
                .orElseThrow(()-> new AppException(ErrorCode.NOT_FOUND));

        UserAnimeList userAnimeList = userAnimeListRepository.findByUserIdAndAnimeId(user.getId(),anime.getId())
                .orElse(new UserAnimeList());

        if (userAnimeList.getUser()==null){
            userAnimeList.setUser(user);
            userAnimeList.setAnime(anime);
        }
        trackingMapper.updateTracking(trackingRequest, userAnimeList);

        return userAnimeListRepository.save(userAnimeList);

    }

}
