package dawflix_api.dto.tmdb;

public record TmdbMovieDto(

        Long id,

        String title,

        String overview,

        String poster_path,

        String backdrop_path,

        Double vote_average,

        String release_date

) {
}
