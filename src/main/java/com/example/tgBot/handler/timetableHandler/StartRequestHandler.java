package com.example.tgBot.handler.timetableHandler;

import com.example.tgBot.handler.UserRequestHandler;
import com.example.tgBot.service.TelegramService;
import com.example.tgBot.models.UserRequest;
import org.springframework.stereotype.Component;
import static com.example.tgBot.TgBotApplication.logger;

@Component
public class StartRequestHandler extends UserRequestHandler {
    public StartRequestHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }
    private final TelegramService telegramService;

    private final String command = "/start";
    private final String message = "Hi, dude";
    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(),command);
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        telegramService.sendMessage(dispatchRequest.getChatID(),message);
        logger.info("request to " + dispatchRequest.getChatID() + ", " + message);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
