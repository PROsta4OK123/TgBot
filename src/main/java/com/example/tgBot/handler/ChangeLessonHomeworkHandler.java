package com.example.tgBot.handler;

import com.example.tgBot.service.TelegramService;
import com.example.tgBot.models.UserRequest;
import com.example.tgBot.models.UserSession;
import org.springframework.stereotype.Component;

@Component
public class ChangeLessonHomeworkHandler extends UserRequestHandler{
    private final String command = "/Change";
    private final TelegramService telegramService;

    public ChangeLessonHomeworkHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(),command);
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        Long chatId = dispatchRequest.getChatID();
        UserSession userSession =dispatchRequest.getUserSession();
        telegramService.sendMessage(chatId,"A не пошёл бы ты нахуй с такими запросами");
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
