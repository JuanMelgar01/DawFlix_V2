package dawflix_api.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    public MovieDto getMovieById(Long movieId) {
        return movieMapper.toMovieDTO(tmdbClient.getMovieById(movieId));
    }

    public MovieDto getFeaturedMovie() {
        List<MovieDto> popularMovies = getPopularMovies();

        int randomIndex = ThreadLocalRandom.current().nextInt(popularMovies.size());
        return popularMovies.get(randomIndex);
    }

    public List<MovieDto> getTopRatedMovies() {
        return tmdbClient
                .getTopRatedMovies()
                .results()
                .stream()
                .map(movieMapper::toMovieDTO)
                .toList();
    }

    public List<MovieDto> getNowPlayingMovies() {
        return tmdbClient
                .getNowPlayingMovies()
                .results()
                .stream()
                .map(movieMapper::toMovieDTO)
                .toList();
    }

    public List<MovieDto> getUpcomingMovies() {
        return tmdbClient
                .getUpcomingMovies()
                .results()
                .stream()
                .map(movieMapper::toMovieDTO)
                .toList();
    }
}
