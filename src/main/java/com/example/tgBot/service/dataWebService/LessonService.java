package com.example.tgBot.service.dataWebService;

import com.example.tgBot.entity.Lesson;
import com.example.tgBot.repository.LessonRepository;
import com.example.tgBot.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    @Autowired
    private LessonRepository repo;
    public List<Lesson> getLessons(){
        return repo.findAll();
    }
    public void addLesson(Lesson lesson){
        repo.save(lesson);
    }

}
