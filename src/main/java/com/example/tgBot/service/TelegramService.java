package com.example.tgBot.service;


import com.example.tgBot.sender.BotSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.tgBot.TgBotApplication.logger;

@Slf4j
@Component
public class TelegramService {
    private final BotSender botSender;

    public TelegramService(BotSender botSender) {
        this.botSender = botSender;
    }
    public void sendMessage(Long chatId, String message, ReplyKeyboard replyKeyboard){
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId.toString())
                .text(message)
                .parseMode(ParseMode.HTML)
                .replyMarkup(replyKeyboard)
                .build();
        execute(sendMessage);
    }

    public void sendMessage(Long chatId, String text) {
        sendMessage(chatId, text, null);
    }
    private void execute(BotApiMethod botApiMethod) {
        try {
            botSender.execute(botApiMethod);
        } catch (TelegramApiException e) {
            logger.warn("Failed to execute");
        }
    }

}
