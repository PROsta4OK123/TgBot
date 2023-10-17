package com.example.tgBot.handler.timetableHandler.customTimetableHandler;

import com.example.tgBot.enums.ConversationStatus;
import com.example.tgBot.handler.UserRequestHandler;
import com.example.tgBot.helper.KeyboardHelper;
import com.example.tgBot.models.UserRequest;
import com.example.tgBot.models.UserSession;
import com.example.tgBot.service.TelegramService;
import com.example.tgBot.service.UserSessionService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static com.example.tgBot.TgBotApplication.logger;

@Component
public class QuantityOfCustomLessonReceivingHandler extends UserRequestHandler {
    private final KeyboardHelper keyboardHelper;
    private final TelegramService telegramService;
    private final UserSessionService userSessionService;

    public QuantityOfCustomLessonReceivingHandler(KeyboardHelper keyboardHelper, TelegramService telegramService, UserSessionService userSessionService) {
        this.keyboardHelper = keyboardHelper;
        this.telegramService = telegramService;
        this.userSessionService = userSessionService;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        String groupChatCommand = "/getcustomhw@Rozklad_domashka_bot";
        String pmCommand = "/getcustomhw";
        return isCommand(request.getUpdate(), pmCommand) || isCommand(request.getUpdate(), groupChatCommand);
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        Long chatId = dispatchRequest.getChatID();
        UserSession userSession = dispatchRequest.getUserSession();
        String message = "Скажи но мені, сонечко, скілько то уроків треба?";

        InlineKeyboardMarkup replyKeyboardMarkup = keyboardHelper.getQuantityOfLessonsButtons();

        telegramService.sendMessageWithGettingId(chatId,message,replyKeyboardMarkup,userSession);
        logger.info("request to " + dispatchRequest.getChatID() + ", " + message);

        userSession.setStatus(ConversationStatus.WAITING_FOR_CUSTOM_LESSON);
        userSessionService.saveSession(userSession.getChatId(), userSession);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
