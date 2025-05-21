package kz.berkut.testing.demoservice.repo;

import kz.berkut.testing.demoservice.entity.TelegramChat;
import kz.berkut.testing.demoservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelegramChatRepo extends JpaRepository<TelegramChat, Long> {
    Optional<TelegramChat> findByChatToken(String token);
    Optional<TelegramChat> findByUser(User user);
}
