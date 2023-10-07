package com.example.tgBot.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Slf4j
@Component
public class BotSender extends DefaultAbsSender {
    private String botToken = "6677821227:AAGEDJXhWAk5mkgGMd5mkQ_SVUp29wvVR0E";

    public BotSender() {
        super(new DefaultBotOptions());
    }

    @Override
    public String getBotToken(){
        return botToken;
    }
}
