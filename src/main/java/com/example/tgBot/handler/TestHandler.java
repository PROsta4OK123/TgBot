package com.example.tgBot.handler;

import com.example.tgBot.models.UserRequest;
import com.example.tgBot.service.TelegramService;
import org.springframework.stereotype.Component;

@Component
public class TestHandler extends UserRequestHandler{
    private final TelegramService telegramService;

    public TestHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(),"/test");
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        telegramService.sendMessage(dispatchRequest.getChatID(), "Test complete");
    }

    @Override
    public boolean isGlobal() {
        return false;
    }
}
