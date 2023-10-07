package com.example.tgBot.service;

import com.example.tgBot.entity.Lesson;
import com.example.tgBot.repository.LessonRepository;
import com.example.tgBot.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class WorkDayMatchingService {
    @Autowired
    LessonRepository lessonRepo;

    public String getTimetable(Long dayOfWeek){
        if (dayOfWeek <= 6){
            LinkedList<Lesson> lessons = lessonRepo.findLessonsByWorkDayId(dayOfWeek);
            List<String> timetableAsList = lessons.stream()
                    .map(s -> s.getLessonName() + ", " + s.getHomework())
                    .toList();
            return getMessage(timetableAsList);
        }else {
            dayOfWeek = 1L;
            getTimetable(dayOfWeek);
        }
        return "Разраб лох, какую-то хуйню накодил. Сори";
    }
    private String getMessage(List<String> timetableAsList){
        String message = null;
        for (int i = 0; i < timetableAsList.toArray().length; i++) {
            message = timetableAsList.get(i) + "\n";
        }
        return message;
    }


}
