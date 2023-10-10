package com.example.tgBot.handler;

import com.example.tgBot.models.UserRequest;
import com.example.tgBot.models.UserSession;
import com.example.tgBot.service.TelegramService;
import com.example.tgBot.service.WorkDayMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.example.tgBot.TgBotApplication.logger;

@Component
public class GetTimetableRequestHandler extends UserRequestHandler {
    private final String command = "/Gethomework";
    private final WorkDayMatchingService workDayMatchingService;
    private final TelegramService telegramService;

    @Autowired
    public GetTimetableRequestHandler(WorkDayMatchingService workDayMatchingService, TelegramService telegramService) {
        this.workDayMatchingService = workDayMatchingService;
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
        String  message = workDayMatchingService.getTimetable((long) LocalDate.now().getDayOfWeek().getValue() + 1);
        telegramService.sendMessage(chatId,message);
        logger.info("request to " + dispatchRequest.getChatID() + ", " + message);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
