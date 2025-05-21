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
     │   ├─ auth/             # JwtProvider, JwtFilter, CustomUserDetailsService, AuthService
     │   ├─ service/          # MessageService, TelegramBotService
     │   ├─ dto/              # DTO для запросов/ответов
     │   └─ controller/       # AuthController, MessageController
     └─ resources/
         ├─ application.properties
         └─ db/changelog/db.changelog-master.yaml


Register
POST /api/auth/register
Content-Type: application/json

{ "login":"user1", "password":"pass", "name":"Ivan" }

Login (get JWT)
POST /api/auth/login
Content-Type: application/json

{ "login":"user1", "password":"pass" }

Response:
{ "token": "<JWT>" }

Generate chat token
POST /api/auth/token
Authorization: Bearer <JWT>

4. Bind Telegram Chat
    Open bot in Telegram (@<BOT_USERNAME>) and press Start.
    Send the generated chatToken to the bot.

5. Send Message
POST /api/messages
Authorization: Bearer <JWT>
Content-Type: application/json

{ "body":"Hello, bot!" }

6.GET /api/messages
Authorization: Bearer <JWT>




