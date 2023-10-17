package com.example.tgBot.handler.timetableHandler.changeTimetableHandler.privatMessageChatHandlesr;

import com.example.tgBot.enums.ConversationStatus;
import com.example.tgBot.handler.UserRequestHandler;
import com.example.tgBot.models.UserRequest;
import com.example.tgBot.models.UserSession;
import com.example.tgBot.repository.LessonRepository;
import com.example.tgBot.service.TelegramService;
import com.example.tgBot.service.UserSessionService;
import org.springframework.stereotype.Component;

@Component
public class ChangingLessonHomeworkHandler extends UserRequestHandler {
    private final UserSessionService userSessionService;
    private final LessonRepository lessonRepository;
    private final TelegramService telegramService;

    public ChangingLessonHomeworkHandler(UserSessionService userSessionService, LessonRepository lessonRepository, TelegramService telegramService) {
        this.userSessionService = userSessionService;
        this.lessonRepository = lessonRepository;
        this.telegramService = telegramService;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return isTextMessage(request.getUpdate()) && ConversationStatus.WAITING_LESSON.equals(request.getUserSession().getStatus()) && lessonRepository.existByLessonName(request.getUpdate().getMessage().getText());
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        Long chatId = dispatchRequest.getChatID();
        telegramService.sendMessage(chatId,"Напиши сюди дз");
        String lessonHw = dispatchRequest.getUpdate().getMessage().getText();

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
