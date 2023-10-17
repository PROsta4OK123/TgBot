package com.example.tgBot.models;

import com.example.tgBot.enums.ConversationStatus;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.ArrayList;


@Builder()
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public @Data class UserSession {

    private Long chatId;
    private String lesson;
    private String text;
    private ConversationStatus status;
    @Builder.Default private CustomTimetable customTimetable = new CustomTimetable();
    private InlineKeyboardMarkup replyKeyboard;
    private int messageId;


}
