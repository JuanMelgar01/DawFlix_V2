package dawflix_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dawflix_api.client.TmdbClient;
import dawflix_api.dto.movie.MovieDto;
import dawflix_api.mapper.MovieMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovieService {

    private final TmdbClient tmdbClient;

    private final MovieMapper movieMapper;

    public List<MovieDto> getPopularMovies() {

        return tmdbClient
                .getPopularMovies()
                .results()
                .stream()
                .map(movieMapper::toMovieDTO)
                .toList();

    }
    
}
