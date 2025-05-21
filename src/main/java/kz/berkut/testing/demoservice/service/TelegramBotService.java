package kz.berkut.testing.demoservice.service;

import jakarta.annotation.PostConstruct;
import kz.berkut.testing.demoservice.config.TelegramProperties;
import kz.berkut.testing.demoservice.repo.TelegramChatRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class TelegramBotService extends TelegramLongPollingBot {

    private final TelegramChatRepo chatRepo;
    private final String botUsername;

    public TelegramBotService(TelegramProperties props, TelegramChatRepo chatRepo) {
        super(props.getToken());
        this.botUsername = props.getUsername();
        this.chatRepo = chatRepo;
    }

    @PostConstruct
    public void init() {
        log.info("TelegramBotService initialized: username='{}'", botUsername);
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText().trim();
            chatRepo.findByChatToken(text).ifPresent(chat -> {
                chat.setChatId(update.getMessage().getChatId().toString());
                chatRepo.save(chat);
                log.info("Bound chatId {} to user {}", chat.getChatId(), chat.getUser().getLogin());
            });
        }
    }

    public void sendToTelegram(String chatId, String text) {
        SendMessage msg = new SendMessage(chatId, text);
        try {
            execute(msg);
        } catch (Exception e) {
            log.error("Failed to send message to Telegram: {}", e.getMessage(), e);
        }
    }
}
