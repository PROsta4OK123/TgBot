package com.example.tgBot.handler.timetableHandler.customTimetableHandler;

import com.example.tgBot.enums.ConversationStatus;
import com.example.tgBot.handler.UserRequestHandler;
import com.example.tgBot.helper.KeyboardHelper;
import com.example.tgBot.models.UserRequest;
import com.example.tgBot.models.UserSession;
import com.example.tgBot.service.TelegramService;
import com.example.tgBot.service.UserSessionService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.tgBot.TgBotApplication.logger;

@Component
public class CustomLessonReceivingHandler extends UserRequestHandler {
    private final UserSessionService userSessionService;
    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;

    public CustomLessonReceivingHandler(UserSessionService userSessionService, TelegramService telegramService, KeyboardHelper keyboardHelper) {
        this.userSessionService = userSessionService;
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return request.getUpdate().hasCallbackQuery() && ConversationStatus.ADDING_CUSTOM_LESSONS_IN_PROGRESS.equals(request.getUserSession().getStatus());
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        Long chatId = dispatchRequest.getChatID();
        UserSession userSession = dispatchRequest.getUserSession();

        if (userSession.getCustomTimetable().getLessonNumber() < userSession.getCustomTimetable().getQuantityOfLessons()) {
            addLesson(userSession, dispatchRequest);
            updateMessage(dispatchRequest,chatId,userSession);
        } else {

            addLesson(userSession, dispatchRequest);
            updateMessage(dispatchRequest,chatId,userSession);

            resetCount(userSession);

            InlineKeyboardMarkup inlineKeyboardMarkup = keyboardHelper.getFinalButton();

            telegramService.deleteMessage(chatId, userSession.getMessageId());

            telegramService.sendMessage(chatId, "Тицяй завершити", inlineKeyboardMarkup);
            logger.info("request to " + dispatchRequest.getChatID() + "EndingInlineKeyboard");

            userSession.setStatus(ConversationStatus.ADDING_CUSTOM_LESSON_COMPLETE);
            userSessionService.saveSession(chatId, userSession);
        }
    }

    @Override
    public boolean isGlobal() {
        return true;
    }

    private void addLesson(UserSession userSession, UserRequest dispatchRequest) {
        String lesson = dispatchRequest.getUpdate().getCallbackQuery().getData();
        userSession.getCustomTimetable().getCustomLessons().add(lesson);
        userSession.getCustomTimetable().setLessonNumber(userSession.getCustomTimetable().getLessonNumber() + 1);
    }

    private void resetCount(UserSession userSession) {
        userSession.getCustomTimetable().resetLessonCounts();
    }
    private void updateMessage(UserRequest request,Long chatId,UserSession userSession){
        String lessonsArrayAsString = Arrays.toString(userSession.getCustomTimetable().getCustomLessons().toArray());
        String message = "Тицяй по кніпочкам, щоб обрати уроки, які хочеш отримати. Наразі вибрано: " + lessonsArrayAsString;
        User user = request.getUpdate().getCallbackQuery().getFrom();

        telegramService.editMessage(chatId,userSession.getMessageId(),message,userSession.getReplyKeyboard());

        logger.info("added lesson " + request.getUpdate().getCallbackQuery().getData() + " to custom timetable array for user " + user.getFirstName() + " " + user.getId());
    }
}
