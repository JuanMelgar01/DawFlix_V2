package dawflix_api.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import dawflix_api.dto.tmdb.TmdbMovieDto;
import dawflix_api.dto.tmdb.TmdbMovieResponseDto;

@Service
public class TmdbClient {

    private final RestClient tmdbRestClient;
    
    public TmdbClient(RestClient tmdbRestClient) {
        this.tmdbRestClient = tmdbRestClient;
    }

    public TmdbMovieResponseDto getPopularMovies() {
        return tmdbRestClient.get()
                .uri( TmdbEndpoints.POPULAR )
                .retrieve()
                .body(TmdbMovieResponseDto.class);
    }

    public TmdbMovieDto getMovieById(Long movieId) {
        return tmdbRestClient.get()
                .uri( TmdbEndpoints.MOVIE_BY_ID, movieId )
                .retrieve()
                .body(TmdbMovieDto.class);
    }

    public TmdbMovieResponseDto getTopRatedMovies() {
        return tmdbRestClient.get()
                .uri(TmdbEndpoints.TOP_RATED)
                .retrieve()
                .body(TmdbMovieResponseDto.class);
    }

    public TmdbMovieResponseDto getNowPlayingMovies() {
        return tmdbRestClient.get()
                .uri(TmdbEndpoints.NOW_PLAYING)
                .retrieve()
                .body(TmdbMovieResponseDto.class);
    }

    public TmdbMovieResponseDto getUpcomingMovies() {
        return tmdbRestClient.get()
                .uri(TmdbEndpoints.UPCOMING)
                .retrieve()
                .body(TmdbMovieResponseDto.class);
    }
}
