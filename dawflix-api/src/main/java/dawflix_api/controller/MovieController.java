package dawflix_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dawflix_api.dto.movie.MovieDto;
import dawflix_api.service.MovieService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/popular")
    public List<MovieDto> getPopularMovies() {

        return movieService.getPopularMovies();

    }

    @GetMapping("/featured")
    public MovieDto getFeaturedMovie() {
        return movieService.getFeaturedMovie();
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<MovieDto>> getTopRatedMovies() {
        return ResponseEntity.ok(movieService.getTopRatedMovies());
    }

    @GetMapping("/now-playing")
    public ResponseEntity<List<MovieDto>> getNowPlayingMovies() {
        return ResponseEntity.ok(movieService.getNowPlayingMovies());
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<MovieDto>> getUpcomingMovies() {
        return ResponseEntity.ok(movieService.getUpcomingMovies());
    }
}
