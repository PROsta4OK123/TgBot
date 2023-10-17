package com.example.tgBot.models;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Getter
@Setter
public class CustomTimetable {
    private ArrayList<String> customLessons = new ArrayList<>();
    private int quantityOfLessons;
    private int lessonNumber = 1;
    public void resetLessons(){
        customLessons.removeAll(customLessons);
    }
    public void resetLessonCounts(){
        lessonNumber = 1;
        quantityOfLessons = 0;
    }
}
