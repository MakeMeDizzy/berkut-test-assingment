package kz.berkut.testing.demoservice.config;

import kz.berkut.testing.demoservice.service.TelegramBotService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotRegistration {

    @Bean
    public TelegramBotsApi telegramBotsApi(TelegramBotService botService) throws Exception {
        var botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(botService);
        return botsApi;
    }
}
