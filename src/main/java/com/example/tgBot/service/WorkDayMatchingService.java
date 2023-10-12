package com.example.tgBot.service;


import com.example.tgBot.entity.Lesson;
import com.example.tgBot.entity.WorkDayMatching;
import com.example.tgBot.repository.WorkDayMatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Component
public class WorkDayMatchingService {
    private final WorkDayMatchingRepository workDayMatchingRepository;
    @Autowired
    public WorkDayMatchingService(WorkDayMatchingRepository workDayMatchingRepository) {
        this.workDayMatchingRepository = workDayMatchingRepository;
    }

    public String getTimetable(Long dayOfWeek){
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
