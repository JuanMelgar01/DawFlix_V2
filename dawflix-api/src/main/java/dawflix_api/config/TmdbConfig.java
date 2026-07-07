package dawflix_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class TmdbConfig {

    @Bean
    RestClient tmdbRestClient(TmdbProperties properties) {

        return RestClient.builder()

                .baseUrl(properties.baseUrl())

                .defaultHeader(
                        HttpHeaders.AUTHORIZATION,
                        "Bearer " + properties.token()
                )

                .defaultHeader(
                        HttpHeaders.ACCEPT,
                        MediaType.APPLICATION_JSON_VALUE
                )

                .build();

    }
    
}
