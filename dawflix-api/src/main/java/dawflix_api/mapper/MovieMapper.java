package dawflix_api.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import dawflix_api.config.TmdbProperties;
import dawflix_api.dto.movie.MovieDto;
import dawflix_api.dto.tmdb.TmdbMovieDto;

@Component
public class MovieMapper {

    private final TmdbProperties tmdbProperties;

    public MovieMapper(TmdbProperties tmdbProperties) {
        this.tmdbProperties = tmdbProperties;
    }
    
    public MovieDto toMovieDTO(TmdbMovieDto movie){
        return new MovieDto(
            movie.id(),

            movie.title(),

            movie.overview(),

            buildImageUrl(movie.poster_path()),

            buildImageUrl(movie.backdrop_path()),

            movie.vote_average(),

            getReleaseYear(movie.release_date())
        );
    }

    private Integer getReleaseYear(String releaseDate) {

        if (releaseDate == null || releaseDate.isBlank()) {
            return null;
        }

        return LocalDate.parse(releaseDate).getYear();

    }

    private String buildImageUrl(String path) {

        if (path == null || path.isBlank()) {
            return null;
        }

        return tmdbProperties.imageUrl() + path;

    }
}
