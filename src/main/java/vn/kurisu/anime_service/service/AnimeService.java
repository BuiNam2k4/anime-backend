package vn.kurisu.anime_service.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.kurisu.anime_service.dto.request.AnimeRequest;
import vn.kurisu.anime_service.dto.request.AnimeUpdateRequest;
import vn.kurisu.anime_service.dto.response.AnimeResponse;
import vn.kurisu.anime_service.dto.response.PageResponse;
import vn.kurisu.anime_service.entity.Anime;
import vn.kurisu.anime_service.entity.Genre;
import vn.kurisu.anime_service.exception.AppException;
import vn.kurisu.anime_service.exception.ErrorCode;
import vn.kurisu.anime_service.mapper.AnimeMapper;
import vn.kurisu.anime_service.repository.AnimeReposiory;
import vn.kurisu.anime_service.repository.GenreRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        if (animeRequest.getGenreMalIds()!=null){
            Set<Genre> genres = genreRepository.findAllByMalIdIn(animeRequest.getGenreMalIds());
            anime.setGenres(genres);
        }
       // List<Genre> foundGenres = genreRepository.findAllById(animeRequest.getGenreIDs());
        //anime.setGenres(new HashSet<>(foundGenres));
        return animeMapper.toAnimeResponse(animeReposiory.save(anime));


    }
    public PageResponse<AnimeResponse> getAllAnime (int page, int size, String keyword){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(page-1,size,sort);
        Page<Anime> pageData;
        if (keyword!= null && !keyword.isBlank()){
            pageData = animeReposiory.findAll(pageable);
        }
        else{
            pageData= animeReposiory.findAll(pageable);
        }
        var dtoList = pageData.map(animeMapper::toAnimeResponse).getContent();
        return PageResponse.<AnimeResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(dtoList)
                .build();
    }

    public void deleteAnime (Long id){
         animeReposiory.deleteById(id);
    }
    public AnimeResponse updateAnime (AnimeUpdateRequest request, Long id){
        Anime anime = animeReposiory.findById(id).orElseThrow(()->new AppException(ErrorCode.EXISTS_EXCEPTION));
        animeMapper.updateAnime(anime,request);
        return animeMapper.toAnimeResponse(animeReposiory.save(anime));

    }
}
