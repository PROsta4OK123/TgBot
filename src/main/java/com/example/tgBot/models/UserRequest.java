package com.example.tgBot.models;

import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data
@Builder
public class UserRequest {
    private Update update;
    private Long chatID;
    private UserSession userSession;

}
