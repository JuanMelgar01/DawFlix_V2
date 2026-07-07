package dawflix_api.dto.movie;

public record MovieDto(

        Long id,

        String title,

        String overview,

        String posterUrl,

        String backdropUrl,

        Double rating,

        Integer releaseYear

) {
}
