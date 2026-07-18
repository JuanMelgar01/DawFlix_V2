package dawflix_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dawflix_api.dto.movie.MovieDto;
import dawflix_api.exception.FavoriteAlreadyExistsException;
import dawflix_api.model.Favorite;
import dawflix_api.model.User;
import dawflix_api.repository.FavoriteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FavoriteService {

    private final AuthenticationService authenticationService;
    private final FavoriteRepository favoriteRepository;
    private final MovieService movieService;

    public void addFavorite (Long movieId) {
        User user = authenticationService.getAuthenticatedUser();
        boolean exists = favoriteRepository.existsByUserAndMovieId(user, movieId);
        if (exists) {
            throw new FavoriteAlreadyExistsException("La película ya está en favoritos");
        }
        Favorite favorite = new Favorite(user, movieId);
        favoriteRepository.save(favorite);
    }

    public List<MovieDto> getFavorites() {
        User user = authenticationService.getAuthenticatedUser();
        List<Favorite> favorites = favoriteRepository.findByUser(user);

        favorites.forEach(f -> System.out.println("Favorite id " + f.getId() + " , movieId " + f.getMovieId()));

        return favorites.stream()
                .map(favorite -> movieService.getMovieById(favorite.getMovieId()))
                .toList();
    }

    public void removeFavorite(Long movieId) {
        User user = authenticationService.getAuthenticatedUser();
        Favorite favorite = favoriteRepository.findByUserAndMovieId(user, movieId)
                .orElseThrow(() -> new RuntimeException("La película no está en favoritos"));
        favoriteRepository.delete(favorite);
    }

    public boolean isFavorite(Long movieId) {
        User user = authenticationService.getAuthenticatedUser();
        return favoriteRepository.existsByUserAndMovieId(user, movieId);
    }
    
}
