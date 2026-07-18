package dawflix_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dawflix_api.model.Favorite;
import dawflix_api.model.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    boolean existsByUserAndMovieId(User user, Long movieId);

    Optional<Favorite> findByUserAndMovieId(User user, Long movieId);
    
    List<Favorite> findByUser(User user);

    long countByUser(User user);

}
