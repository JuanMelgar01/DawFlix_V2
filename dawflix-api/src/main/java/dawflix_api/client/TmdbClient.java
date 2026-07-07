package dawflix_api.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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
}
