package kz.berkut.testing.demoservice.service;

import kz.berkut.testing.demoservice.dto.MessageResponse;
import kz.berkut.testing.demoservice.entity.Message;
import kz.berkut.testing.demoservice.entity.TelegramChat;
import kz.berkut.testing.demoservice.entity.User;
import kz.berkut.testing.demoservice.repo.MessageRepo;
import kz.berkut.testing.demoservice.repo.TelegramChatRepo;
import kz.berkut.testing.demoservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final UserRepo userRepo;
    private final TelegramChatRepo chatRepo;
    private final MessageRepo messageRepo;
    private final TelegramBotService botService;

    @Transactional
    public void processOutgoing(String login, String body) {
        User user = userRepo.findByLogin(login).orElseThrow();
        Message msg = Message.builder()
                .user(user)
                .body(body)
                .build();
        messageRepo.save(msg);

        TelegramChat chat = chatRepo.findByUser(user)
                .orElseThrow(() -> new IllegalStateException("Chat not bound"));
        String text = String.format("%s, я получил от тебя сообщение:%n%s", user.getName(), body);
        botService.sendToTelegram(chat.getChatId(), text);
    }

    @Transactional(readOnly = true)
    public List<MessageResponse> getMessages(String login) {
        User user = userRepo.findByLogin(login).orElseThrow();
        return messageRepo.findByUserOrderByCreatedAtDesc(user).stream()
                .map(m -> new MessageResponse(m.getCreatedAt(), m.getBody()))
                .toList();
    }
}

