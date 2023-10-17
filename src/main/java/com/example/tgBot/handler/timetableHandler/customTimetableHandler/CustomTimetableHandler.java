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
public class CustomTimetableHandler extends UserRequestHandler {
    private final KeyboardHelper keyboardHelper;
    private final TelegramService telegramService;
    private final UserSessionService userSessionService;

    public CustomTimetableHandler(KeyboardHelper keyboardHelper, TelegramService telegramService, UserSessionService userSessionService) {
        this.keyboardHelper = keyboardHelper;
        this.telegramService = telegramService;
        this.userSessionService = userSessionService;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return request.getUpdate().hasCallbackQuery() && ConversationStatus.WAITING_FOR_CUSTOM_LESSON.equals(request.getUserSession().getStatus());
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        Long chatId = dispatchRequest.getChatID();
        UserSession userSession = dispatchRequest.getUserSession();

        dispatchRequest.getUserSession().getCustomTimetable().setQuantityOfLessons(Integer.parseInt(dispatchRequest.getUpdate().getCallbackQuery().getData()));
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardHelper.getLessonsInChat();
        String message = "Тицяй по кніпочкам, щоб обрати уроки, які хочеш отримати";

        telegramService.sendMessageWithGettingId(chatId,message, inlineKeyboardMarkup,userSession);
        logger.info("request to " + dispatchRequest.getChatID() + ", " + message);

        userSession.setReplyKeyboard(inlineKeyboardMarkup);
        userSession.setStatus(ConversationStatus.ADDING_CUSTOM_LESSONS_IN_PROGRESS);
        userSessionService.saveSession(chatId, userSession);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
