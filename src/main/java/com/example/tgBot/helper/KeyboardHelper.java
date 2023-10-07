package com.example.tgBot.helper;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class KeyboardHelper {

    public ReplyKeyboardMarkup getScheduleButton(){
        List<KeyboardButton> schedule = List.of(
                new KeyboardButton("Розклад")
        );
        KeyboardRow row = new KeyboardRow(schedule);
        KeyboardRow row1 = new KeyboardRow(List.of(new KeyboardButton("Cansel")));
        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(row,row1))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
    public ReplyKeyboardMarkup getLessonButton(List<String> lessons) {
        List<KeyboardButton> buttons = getLessons(lessons);
        KeyboardRow row = new KeyboardRow(buttons);
        KeyboardRow row1 = new KeyboardRow(List.of(new KeyboardButton("Cansel")));
        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(row, row1))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }

    private List<KeyboardButton> getLessons(List<String> lessons) {
        List<KeyboardButton> keyboardButtonList = new LinkedList<>();
        keyboardButtonList = lessons.stream()
                .map(KeyboardButton::new)
                .collect(Collectors.toList());
        return keyboardButtonList;
    }
}
