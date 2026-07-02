package dawflix_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dawflix_api.dto.ApiResponse;
import dawflix_api.dto.LoginData;
import dawflix_api.dto.LoginRequest;
import dawflix_api.dto.RegisterRequest;
import dawflix_api.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody RegisterRequest registerRequest) {
        ApiResponse<String> response = userService.register(registerRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailUnavailable(@RequestParam String email) {
        System.out.println("Email recibido en el controlador: " + email);
        Boolean isUnavailable = userService.checkEmailUnavailable(email);
        return ResponseEntity.ok(isUnavailable);
    }

    @GetMapping("/verify")
    public ResponseEntity<ApiResponse<Boolean>> verifyUser(@RequestParam String token) {
        ApiResponse<Boolean> response = userService.verify(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginData>> loginUser (@RequestBody LoginRequest loginRequest){
        ApiResponse<LoginData> response = userService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
