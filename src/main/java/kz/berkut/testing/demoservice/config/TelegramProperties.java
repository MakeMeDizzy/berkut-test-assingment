package kz.berkut.testing.demoservice.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "telegram.bot")
public class TelegramProperties {

    private final String username;
    private final String token;

    public TelegramProperties(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
