package com.example.tgBot;

import com.example.tgBot.enums.ConversationStatus;
import com.example.tgBot.service.UserSessionService;
import com.example.tgBot.models.UserRequest;
import com.example.tgBot.models.UserSession;
import com.example.tgBot.sender.BotSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.logging.Logger;

import static com.example.tgBot.TgBotApplication.logger;

@Slf4j
@Component
public class TgBot extends TelegramLongPollingBot {


    private String botToken = "6477147253:AAFC-vEyC69vCgRp6d3LnxQlq_hYYnHdIZU";

    private String botUsername = "rozklad-testBot";

    public TgBot(Dispatcher dispatcher, UserSessionService userSessionService) {
        this.dispatcher = dispatcher;
        this.userSessionService = userSessionService;
    }

    BotSender botSender;
    private final Dispatcher dispatcher;
    private final UserSessionService userSessionService;

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            Long userId = update.getMessage().getFrom().getId();
            String userFirstName = update.getMessage().getFrom().getFirstName();
            String textFromUser = update.getMessage().getText();

            logger.info("[{}, {}] : {}", userId, userFirstName, textFromUser);

            Long chatId = update.getMessage().getChatId();
            UserSession session = userSessionService.getSession(chatId);

            UserRequest userRequest = UserRequest
                    .builder()
                    .update(update)
                    .userSession(session)
                    .chatID(chatId)
                    .build();

            boolean dispatched = dispatcher.dispatch(userRequest);

            if (!dispatched) {
                logger.warn("Unexpected update from user");
            }
        } else if (update.hasCallbackQuery()) {
            Long userId = update.getCallbackQuery().getFrom().getId();
            String userFirstName = update.getCallbackQuery().getFrom().getFirstName();
            String textFromUser = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            UserSession session = userSessionService.getSession(chatId);
            ConversationStatus conversationStatus = session.getStatus();

            logger.info("[{}, {}, {}] : {}", userId, userFirstName, textFromUser, conversationStatus);


            UserRequest userRequest = UserRequest
                    .builder()
                    .update(update)
                    .userSession(session)
                    .chatID(chatId)
                    .build();

            boolean dispatched = dispatcher.dispatch(userRequest);

            if (!dispatched) {
                logger.warn("Unexpected update from user");
            }
        } else {
            logger.warn("Unexpected update from user");
        }
    }
    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
