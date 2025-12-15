package vn.kurisu.anime_service.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.kurisu.anime_service.dto.request.AnimeRequest;
import vn.kurisu.anime_service.dto.response.AnimeResponse;
import vn.kurisu.anime_service.entity.Anime;
import vn.kurisu.anime_service.entity.Genre;
import vn.kurisu.anime_service.exception.AppException;
import vn.kurisu.anime_service.exception.ErrorCode;
import vn.kurisu.anime_service.mapper.AnimeMapper;
import vn.kurisu.anime_service.repository.AnimeReposiory;
import vn.kurisu.anime_service.repository.GenreRepository;

import java.util.HashSet;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
public class AnimeService {
    private AnimeReposiory animeReposiory;
    private GenreRepository genreRepository;
    private AnimeMapper animeMapper;

    public AnimeResponse createAnime (AnimeRequest animeRequest){
        if (animeReposiory.existsByTitle(animeRequest.getTitle())){
            throw new AppException(ErrorCode.EXISTS_EXCEPTION);
        }
        Anime anime = animeMapper.toAnime(animeRequest);
        List<Genre> foundGenres = genreRepository.findAllById(animeRequest.getGenreIDs());
        anime.setGenres(new HashSet<>(foundGenres));
        return animeMapper.toAnimeResponse(animeReposiory.save(anime));


    }
    public List<AnimeResponse> getAllAnime (){
        return animeReposiory.findAll().stream().map(animeMapper::toAnimeResponse).toList();
    }

    public void deleteAnime (Long id){
         animeReposiory.deleteById(id);
    }
}
