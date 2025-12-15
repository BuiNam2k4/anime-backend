package vn.kurisu.anime_service.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.kurisu.anime_service.dto.request.GenreRequest;
import vn.kurisu.anime_service.dto.response.GenreResponse;
import vn.kurisu.anime_service.entity.Genre;
import vn.kurisu.anime_service.mapper.GenreMapper;
import vn.kurisu.anime_service.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class GenreService {
    private GenreRepository genreRepository;
    private GenreMapper genreMapper;
    public GenreResponse createGenre(GenreRequest genreRequest){
        if (genreRepository.existsByName(genreRequest.getName())){
            throw new RuntimeException("The loai nay da ton tai");
        }
        Genre genre = genreMapper.toGenre(genreRequest);
        return genreMapper.toGenreResponse(genreRepository.save(genre));
    }
    public List<GenreResponse> getAllGenre(){
        return genreRepository.findAll().stream().map(genreMapper::toGenreResponse).toList();
    }
}
