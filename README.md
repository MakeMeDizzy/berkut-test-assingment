# DemoService

**Spring Boot (Java 17) демо-приложение** с регистрацией/авторизацией (JWT), сохранением сообщений и интеграцией Telegram Bot (Long Polling).

---

## ✨ Возможности

- **Регистрация** пользователей (login, password, name)
- **Авторизация** через JWT
- **Генерация chat-токена** для привязки Telegram-чата
- **Long Polling-бот**: привязка чата, приём и отправка сообщений
- **REST API** для отправки и получения собственных сообщений
- **PostgreSQL** + **Liquibase** для миграций
- **SLF4J/Logback** для логирования

---

## 📁 Структура проекта

```text
src/
 └─ main/
     ├─ java/kz/berkut/testing/demoservice/
     │   ├─ DemoServiceApplication.java
     │   ├─ config/
     │   │   ├─ SecurityConfig.java
     │   │   ├─ JwtProperties.java
     │   │   ├─ TelegramProperties.java
     │   │   └─ TelegramBotRegistration.java
     │   ├─ entity/           # JPA-сущности: User, TelegramChat, Message
     │   ├─ repo/             # Spring Data репозитории
     │   ├─ security/         # JwtProvider, JwtFilter, CustomUserDetailsService
     │   ├─ auth/             # AuthService, AuthController
     │   ├─ service/          # MessageService, TelegramBotService
     │   ├─ dto/              # DTO для запросов/ответов
     │   └─ controller/       # MessageController
     └─ resources/
         ├─ application.properties
         └─ db/changelog/db.changelog-master.yaml


