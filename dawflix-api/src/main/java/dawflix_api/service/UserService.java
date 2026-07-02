package dawflix_api.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dawflix_api.dto.ApiResponse;
import dawflix_api.dto.LoginData;
import dawflix_api.dto.LoginRequest;
import dawflix_api.dto.RegisterRequest;
import dawflix_api.dto.UserDTO;
import dawflix_api.exception.UserAlreadyExistsException;
import dawflix_api.mapper.UserMapper;
import dawflix_api.model.Role;
import dawflix_api.model.User;
import dawflix_api.model.VerificationToken;
import dawflix_api.repository.RoleRepository;
import dawflix_api.repository.TokenRepository;
import dawflix_api.repository.UserRepository;
import dawflix_api.security.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;

    public ApiResponse<String> register(RegisterRequest request){

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(
                "El correo electrónico ya está en uso"
            );
        }

        String roleName = Optional.ofNullable(request.getRole())
        .map(String::toUpperCase)
        .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)
        .orElse("ROLE_USER");

        Role role = roleRepository.findByName(roleName)
            .orElseThrow(() -> new IllegalArgumentException("El role " + roleName + " no existe"));

        User usuario = new User();
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setEnabled(false);
        usuario.setRole(role);

        userRepository.save(usuario);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(usuario);
        verificationToken.setExpiration(LocalDateTime.now().plusHours(2));
        verificationToken.setUsed(false);

        tokenRepository.save(verificationToken);
        emailService.sendVerificationEmail(usuario.getEmail(), token);

        return new ApiResponse<String>(
            0,
            "Usuario registrado correctamente",
            "Revisa tu email para activar la cuenta"
        );

    }

    public boolean checkEmailUnavailable(String email) {
        return userRepository.existsByEmail(email);
    }

    public ApiResponse<Boolean> verify(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> new IllegalArgumentException("Token no válido"));

        if (verificationToken.isUsed()) {
            return new ApiResponse<>(1, "Token ya usado", false);
        }

        if (verificationToken.getExpiration().isBefore(LocalDateTime.now())) {
            return new ApiResponse<>(2, "Token expirado", false);
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        verificationToken.setUsed(true);
        tokenRepository.save(verificationToken);

        return new ApiResponse<>(0, "Usuario verificado correctamente", true);
    }

    public ApiResponse<LoginData> login (LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        User user = userRepository.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        UserDTO userDTO = userMapper.toDTO(user);
        LoginData logindata = new LoginData(userDTO, accessToken, refreshToken);
        return new ApiResponse<LoginData>(0, "Login correcto", logindata);
    }
}
