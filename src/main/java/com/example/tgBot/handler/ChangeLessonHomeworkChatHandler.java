package com.example.tgBot.handler;

import com.example.tgBot.enums.ConversationStatus;
import com.example.tgBot.helper.KeyboardHelper;
import com.example.tgBot.models.UserRequest;
import com.example.tgBot.models.UserSession;
import com.example.tgBot.repository.LessonRepository;
import com.example.tgBot.service.TelegramService;
import com.example.tgBot.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Component
public class ChangeLessonHomeworkChatHandler extends UserRequestHandler{
    @Autowired
    public ChangeLessonHomeworkChatHandler(TelegramService telegramService, KeyboardHelper keyboardHelper, LessonRepository lessonRepository, UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
        this.userSessionService = userSessionService;
    }
    private final String command = "/change@Rozklad_domashka_bot";
    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;
    private final UserSessionService userSessionService;
    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(),command);
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        Long chatId = dispatchRequest.getChatID();
        InlineKeyboardMarkup replyKeyboardMarkup = keyboardHelper.getLessonsInChat();
        telegramService.sendMessage(chatId,"Оберіть урок для зміни",replyKeyboardMarkup);

        UserSession userSession = dispatchRequest.getUserSession();
        userSession.setStatus(ConversationStatus.WAITING_LESSON);
        userSessionService.saveSession(userSession.getChatId(), userSession);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }

}
