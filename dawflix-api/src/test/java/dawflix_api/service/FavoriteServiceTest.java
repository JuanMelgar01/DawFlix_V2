package dawflix_api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dawflix_api.exception.FavoriteAlreadyExistsException;
import dawflix_api.model.Favorite;
import dawflix_api.model.User;
import dawflix_api.repository.FavoriteRepository;

@ExtendWith(MockitoExtension.class)
public class FavoriteServiceTest {

    @Mock
    private FavoriteRepository favoriteRepository;
    @Mock
    private AuthenticationService authenticationService;
    @Mock
    private MovieService movieService;
    
    @InjectMocks
    private FavoriteService favoriteService;

    @Test
    void deberiaAgregarFavorito() {
        // Given

        Long movieId = 123L;

        User user = new User();
        user.setId(1L);


        when(authenticationService.getAuthenticatedUser())
                .thenReturn(user);


        when(favoriteRepository.existsByUserAndMovieId(user, movieId))
                .thenReturn(false);



        // When

        favoriteService.addFavorite(movieId);



        // Then

        ArgumentCaptor<Favorite> captor = ArgumentCaptor.forClass(Favorite.class);
        verify(favoriteRepository).save(captor.capture());

        Favorite savedFavorite = captor.getValue();
        assertEquals(user, savedFavorite.getUser());
        assertEquals(movieId, savedFavorite.getMovieId());
    
    }

    @Test
    void shouldRemoveFavoriteWhenExists(){

        Long movieId = 123L;

        User user = new User();
        user.setId(1L);


        Favorite favorite = new Favorite(user, movieId);



        when(authenticationService.getAuthenticatedUser())
                .thenReturn(user);


        when(favoriteRepository.findByUserAndMovieId(user, movieId))
                .thenReturn(Optional.of(favorite));



        favoriteService.removeFavorite(movieId);



        verify(favoriteRepository)
                .delete(favorite);

    }

    @Test
    void shouldThrowExceptionWhenRemovingNonExistingFavorite(){

        Long movieId = 123L;

        User user = new User();
        user.setId(1L);



        when(authenticationService.getAuthenticatedUser())
                .thenReturn(user);


        when(favoriteRepository.findByUserAndMovieId(user, movieId))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> favoriteService.removeFavorite(movieId)
        );


        verify(favoriteRepository, never())
                .delete(any());

    }

}
