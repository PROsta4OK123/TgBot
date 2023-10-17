package com.example.tgBot.service;


import com.example.tgBot.models.UserSession;
import com.example.tgBot.sender.BotSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
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
    public void sendMessageWithGettingId(Long chatId, String message, ReplyKeyboard replyKeyboard, UserSession userSession){
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId.toString())
                .text(message)
                .parseMode(ParseMode.HTML)
                .replyMarkup(replyKeyboard)
                .build();
        userSession.setMessageId(execute(sendMessage));
    }
    public void deleteMessage(Long chatId, Integer messageId){
        execute(new DeleteMessage(chatId.toString(),messageId));
    }
    public void editMessage(Long chatId, Integer messageId, String text, InlineKeyboardMarkup inlineKeyboardMarkup){
        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(inlineKeyboardMarkup)
                .text(text)
                .build();
        execute(editMessageText);
    }

    private Integer execute(SendMessage botApiMethod){
        try {
            Message message = botSender.execute(botApiMethod);
            return message.getMessageId();
        }catch (TelegramApiException e) {
            logger.warn("Failed to execute");
        }
        logger.error("failed to get message id");
        return null;
    }
    private void execute(BotApiMethod botApiMethod) {
        try {
            botSender.execute(botApiMethod);
        }catch (TelegramApiException e) {
            logger.warn("Failed to execute");
        }
    }

}
