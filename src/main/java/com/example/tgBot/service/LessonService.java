package com.example.tgBot.service;

import com.example.tgBot.entity.Lesson;
import com.example.tgBot.entity.WorkDayMatching;
import com.example.tgBot.repository.LessonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {
    private final LessonRepository repo;
    @Autowired
    public LessonService(LessonRepository repo) {
        this.repo = repo;
    }
    public void addLesson(Lesson lesson){
        repo.save(lesson);
    }

    public List<Lesson> getLessons() {
        return repo.findAll();
    }
    public void deleteLesson(Long id){
        repo.deleteById(id);
    }
    @Transactional
    public void updateLessonHomework(String hw, String lesson) {
        Lesson lesson1 = repo.findByLessonName(lesson);
        lesson1.setHomework(hw);
        repo.save(lesson1);
    }
    public String getTimetable(List<String> listOfLessons){
        List<Lesson> lessons = new ArrayList<>();
        for (String lesson : listOfLessons){
            lessons.add(repo.findByLessonName(lesson));
        }
        List<String> timetableAsList = lessons.stream()
                .map(s -> s.getLessonName() + ", " + s.getHomework())
                .toList();
        return getMessage(timetableAsList);
    }
    private String getMessage(List<String> timetableAsList){
        String message = "";
        for (String s : timetableAsList) {
            message = message + s + '\n';
        }
        return message;
    }

}
