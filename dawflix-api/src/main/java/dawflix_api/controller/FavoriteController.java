package dawflix_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dawflix_api.dto.movie.MovieDto;
import dawflix_api.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@AllArgsConstructor
@RequestMapping("/api/favorites")
public class FavoriteController {
    
    private final FavoriteService favoriteService;

    @PostMapping("/{movieId}")
    public ResponseEntity<Void> addFavorite(@PathVariable Long movieId) {
        favoriteService.addFavorite(movieId);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<MovieDto>> getFavorites() {
        return ResponseEntity.ok(favoriteService.getFavorites());
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long movieId) {
        favoriteService.removeFavorite(movieId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Boolean> isFavorite(@PathVariable Long movieId) {
        boolean isFavorite = favoriteService.isFavorite(movieId);
        return ResponseEntity.ok(isFavorite);
    }
    
}
