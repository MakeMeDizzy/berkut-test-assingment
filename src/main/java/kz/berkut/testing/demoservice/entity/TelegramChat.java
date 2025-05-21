package kz.berkut.testing.demoservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "telegram_chat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelegramChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "chat_token", nullable = false, unique = true, length = 100)
    private String chatToken;
}
