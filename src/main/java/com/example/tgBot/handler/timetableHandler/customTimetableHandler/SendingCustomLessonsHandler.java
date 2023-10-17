package com.example.tgBot.handler.timetableHandler.customTimetableHandler;

import com.example.tgBot.enums.ConversationStatus;
import com.example.tgBot.handler.UserRequestHandler;
import com.example.tgBot.models.UserRequest;
import com.example.tgBot.models.UserSession;
import com.example.tgBot.service.LessonService;
import com.example.tgBot.service.TelegramService;
import org.springframework.stereotype.Component;

import static com.example.tgBot.TgBotApplication.logger;
@Component
public class SendingCustomLessonsHandler extends UserRequestHandler {
    private final TelegramService telegramService;

    private final LessonService lessonService;

    public SendingCustomLessonsHandler(TelegramService telegramService, LessonService lessonService) {
        this.telegramService = telegramService;
        this.lessonService = lessonService;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return request.getUpdate().hasCallbackQuery() && ConversationStatus.ADDING_CUSTOM_LESSON_COMPLETE.equals(request.getUserSession().getStatus());
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        Long chatId = dispatchRequest.getChatID();
        UserSession userSession = dispatchRequest.getUserSession();
        String message = lessonService.getTimetable(userSession.getCustomTimetable().getCustomLessons());

        telegramService.sendMessage(chatId,message);
        userSession.getCustomTimetable().resetLessons();

        logger.info("request to " + dispatchRequest.getChatID() + ", " + message);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
