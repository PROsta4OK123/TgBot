package com.example.tgBot.service;


import com.example.tgBot.entity.Lesson;
import com.example.tgBot.entity.WorkDayMatching;
import com.example.tgBot.repository.LessonRepository;
import com.example.tgBot.repository.WorkDayMatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Component
public class WorkDayMatchingService {
    @Autowired
    WorkDayMatchingRepository workDayMatchingRepository;

    public String getTimetable(Long dayOfWeek){
        String message;
        if (dayOfWeek <= 6L){
            List<Lesson> lessons = workDayMatchingRepository.findAllByWorkDayId(dayOfWeek).stream()
                    .map(WorkDayMatching::getLesson)
                    .toList();
            List<String> timetableAsList = lessons.stream()
                    .map(s -> s.getLessonName() + ", " + s.getHomework())
                    .toList();
            System.out.println(timetableAsList.get(0));
            return getMessage(timetableAsList);
        }else {
            dayOfWeek = 1L;
            return getTimetable(dayOfWeek);
        }
    }
    private String getMessage(List<String> timetableAsList){
        String message = "";
        for (String s : timetableAsList) {
            message = message + s + '\n';
        }
        return message;
    }


}
