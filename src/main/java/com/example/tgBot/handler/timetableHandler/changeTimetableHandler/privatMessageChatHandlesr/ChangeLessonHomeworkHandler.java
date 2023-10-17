package com.example.tgBot.handler.timetableHandler.changeTimetableHandler.privatMessageChatHandlesr;

import com.example.tgBot.enums.ConversationStatus;
import com.example.tgBot.handler.UserRequestHandler;
import com.example.tgBot.helper.KeyboardHelper;
import com.example.tgBot.models.UserSession;
import com.example.tgBot.repository.LessonRepository;
import com.example.tgBot.service.TelegramService;
import com.example.tgBot.models.UserRequest;
import com.example.tgBot.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Component
public class ChangeLessonHomeworkHandler extends UserRequestHandler {
    @Autowired
    public ChangeLessonHomeworkHandler(TelegramService telegramService, KeyboardHelper keyboardHelper, LessonRepository lessonRepository, UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
        this.userSessionService = userSessionService;
    }
    private final String command = "/change";
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
        ReplyKeyboardMarkup replyKeyboardMarkup = keyboardHelper.getLessonButtons();
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
