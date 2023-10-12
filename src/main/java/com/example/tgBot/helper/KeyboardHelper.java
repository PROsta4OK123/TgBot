package com.example.tgBot.helper;

import com.example.tgBot.repository.LessonRepository;
import com.fasterxml.jackson.databind.type.TypeBindings;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KeyboardHelper {
    public KeyboardHelper(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }
    private final LessonRepository lessonRepository;

    public ReplyKeyboardMarkup getLessonButton() {
        List<String> lessons = getLessons();
        List<KeyboardRow> keyboardRows = new LinkedList<>();
        for (String lesson : lessons) {
            KeyboardButton button = new KeyboardButton(lesson);
            KeyboardRow row = new KeyboardRow();
            row.add(button);
            keyboardRows.add(row);
        }

        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboardRows)
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
    public InlineKeyboardMarkup getLessonsInChat(){
        List<String> lessons = getLessons();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String lesson : lessons) {
            InlineKeyboardButton button = InlineKeyboardButton.builder()
                    .callbackData(lesson)
                    .text(lesson)
                    .build();
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(button);
            keyboard.add(row);
        }

        return InlineKeyboardMarkup.builder()
                .keyboard(keyboard)
                .build();
    }

    private List<String> getLessons() {
        return lessonRepository.findAllLessonName();
    }
}
