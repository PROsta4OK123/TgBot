package com.example.tgBot.helper;

import com.example.tgBot.repository.LessonRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class KeyboardHelper {
    public KeyboardHelper(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }
    private final LessonRepository lessonRepository;

    public InlineKeyboardMarkup getQuantityOfLessonsButtons() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        int maxQuantityOfLessons = 7;
        for (int i = 1; i <= maxQuantityOfLessons; i++) {
            InlineKeyboardButton button = InlineKeyboardButton.builder()
                    .callbackData(Integer.toString(i))
                    .text(Integer.toString(i))
                    .build();
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(button);
            keyboard.add(row);
        }
        return InlineKeyboardMarkup.builder()
                .keyboard(keyboard)
                .build();
    }
    public InlineKeyboardMarkup getFinalButton(){
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .callbackData("Ending")
                .text("Завершити")
                .build();

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(button);
        keyboard.add(row);

        return InlineKeyboardMarkup.builder()
                .keyboard(keyboard)
                .build();
    }

    public ReplyKeyboardMarkup getLessonButtons() {
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
