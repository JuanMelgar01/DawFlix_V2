package dawflix_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tmdb")
public record TmdbProperties(

        String baseUrl,

        String imageUrl,

        String token

) {
}
