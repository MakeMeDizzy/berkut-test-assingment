package kz.berkut.testing.demoservice.controller;

import jakarta.validation.Valid;
import kz.berkut.testing.demoservice.auth.AuthService;
import kz.berkut.testing.demoservice.dto.ChatTokenResponse;
import kz.berkut.testing.demoservice.dto.JwtResponse;
import kz.berkut.testing.demoservice.dto.LoginRequest;
import kz.berkut.testing.demoservice.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest req) {
        authService.register(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest req) {
        String jwt = authService.login(req);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/token")
    public ResponseEntity<ChatTokenResponse> createChatToken(Authentication auth) {
        String chatToken = authService.generateChatToken(auth.getName());
        return ResponseEntity.ok(new ChatTokenResponse(chatToken));
    }
}

