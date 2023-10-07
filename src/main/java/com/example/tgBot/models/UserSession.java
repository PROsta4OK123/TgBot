package com.example.tgBot.models;

import com.example.tgBot.enums.ConversationStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class UserSession {
    private Long chatId;
    private String lesson;
    private String text;
    private ConversationStatus status;

}
