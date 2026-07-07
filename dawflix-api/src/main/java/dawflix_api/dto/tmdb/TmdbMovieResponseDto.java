package dawflix_api.dto.tmdb;

import java.util.List;

public record TmdbMovieResponseDto(

        List<TmdbMovieDto> results

) {
}
