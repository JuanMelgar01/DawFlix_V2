package dawflix_api.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import dawflix_api.exception.UserNotAuthenticatedException;
import dawflix_api.model.User;

@Service
public class AuthenticationService {
    
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if( authentication == null || !authentication.isAuthenticated()){
            throw new UserNotAuthenticatedException("Usuario no autenticado");
        }

        Object principal = authentication.getPrincipal();

        if(!(principal instanceof User user)) {
            throw new UserNotAuthenticatedException("Usuario no autenticado");
        }

        return user;
    }
}
