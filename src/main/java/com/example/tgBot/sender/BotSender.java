package com.example.tgBot.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Slf4j
@Component
public class BotSender extends DefaultAbsSender {
    private String botToken = "6477147253:AAFC-vEyC69vCgRp6d3LnxQlq_hYYnHdIZU";

    public BotSender() {
        super(new DefaultBotOptions());
    }

    @Override
    public String getBotToken(){
        return botToken;
    }
}
