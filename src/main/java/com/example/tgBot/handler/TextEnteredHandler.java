package com.example.tgBot.handler;

import com.example.tgBot.enums.ConversationStatus;
import com.example.tgBot.models.UserRequest;
import com.example.tgBot.models.UserSession;
import com.example.tgBot.service.LessonService;
import com.example.tgBot.service.TelegramService;
import com.example.tgBot.service.UserSessionService;
import org.springframework.stereotype.Component;

@Component
public class TextEnteredHandler extends UserRequestHandler{
    private final TelegramService telegramService;
    private final LessonService lessonService;
    private final UserSessionService userSessionService;


    public TextEnteredHandler(TelegramService telegramService, LessonService lessonService, UserSessionService userSessionService) {
        this.telegramService = telegramService;
        this.lessonService = lessonService;
        this.userSessionService = userSessionService;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return isTextMessage(request.getUpdate()) && ConversationStatus.WAITING_FOR_TEXT.equals(request.getUserSession().getStatus());
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        Long chatId = dispatchRequest.getChatID();
        telegramService.sendMessage(chatId,"Домашнє завдання додано");

        UserSession userSession = dispatchRequest.getUserSession();
        lessonService.updateLessonHomework(dispatchRequest.getUpdate().getMessage().getText(),  userSession.getLesson());
        userSession.setStatus(ConversationStatus.CONVERSATION_STARTED);
        userSessionService.saveSession(dispatchRequest.getChatID(),dispatchRequest.getUserSession());
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
