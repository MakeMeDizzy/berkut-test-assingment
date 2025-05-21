package kz.berkut.testing.demoservice.auth;

import kz.berkut.testing.demoservice.dto.LoginRequest;
import kz.berkut.testing.demoservice.dto.RegisterRequest;
import kz.berkut.testing.demoservice.entity.TelegramChat;
import kz.berkut.testing.demoservice.entity.User;
import kz.berkut.testing.demoservice.repo.TelegramChatRepo;
import kz.berkut.testing.demoservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final TelegramChatRepo chatRepo;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest req) {
        if (userRepo.existsByLogin(req.login())) {
            throw new IllegalArgumentException("Такой логин уже занят");
        }
        User u = new User();
        u.setLogin(req.login());
        u.setPasswordHash(passwordEncoder.encode(req.password()));
        u.setName(req.name());
        userRepo.save(u);
    }

    public String login(LoginRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.login(), req.password())
        );
        return jwtProvider.generateToken(req.login());
    }

    public String generateChatToken(String login) {
        User user = userRepo.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        chatRepo.findByUser(user).ifPresent(chatRepo::delete);
        String token = UUID.randomUUID().toString();
        TelegramChat chat = new TelegramChat();
        chat.setUser(user);
        chat.setChatToken(token);
        chatRepo.save(chat);
        return token;
    }
}
