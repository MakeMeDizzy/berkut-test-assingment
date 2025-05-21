package kz.berkut.testing.demoservice.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private final String secret;
    private final long expiration;

    public JwtProperties(String secret, long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }
}
