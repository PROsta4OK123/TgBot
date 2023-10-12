package com.example.tgBot.handler;

import com.example.tgBot.enums.ConversationStatus;
import com.example.tgBot.models.UserRequest;
import com.example.tgBot.models.UserSession;
import com.example.tgBot.repository.LessonRepository;
import com.example.tgBot.service.TelegramService;
import com.example.tgBot.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeLessonHomeworkInChatHandler extends UserRequestHandler{
    private final LessonRepository lessonRepository;
    private final TelegramService telegramService;
    private final UserSessionService userSessionService;
    @Autowired
    public ChangeLessonHomeworkInChatHandler(LessonRepository lessonRepository, TelegramService telegramService, UserSessionService userSessionService) {
        this.lessonRepository = lessonRepository;
        this.telegramService = telegramService;
        this.userSessionService = userSessionService;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return request.getUpdate().hasCallbackQuery() && ConversationStatus.WAITING_LESSON.equals(request.getUserSession().getStatus());
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        System.out.println(dispatchRequest.getUpdate().getCallbackQuery().getData());
        Long chatId = dispatchRequest.getChatID();
        telegramService.sendMessage(chatId,"Напиши сюди дз (Спочатку натисни кніпоньку відповісти)");
        String lessonHw = dispatchRequest.getUpdate().getCallbackQuery().getData();

        UserSession userSession = dispatchRequest.getUserSession();
        userSession.setLesson(lessonHw);
        userSession.setStatus(ConversationStatus.WAITING_FOR_TEXT);
        userSessionService.saveSession(userSession.getChatId(), userSession);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
