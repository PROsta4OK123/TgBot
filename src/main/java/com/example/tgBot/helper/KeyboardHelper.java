package com.example.tgBot.helper;

import com.example.tgBot.repository.LessonRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

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

    private List<String> getLessons() {
        return lessonRepository.findAllLessonName();
    }
}
