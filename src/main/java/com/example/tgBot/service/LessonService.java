package com.example.tgBot.service;

import com.example.tgBot.entity.Lesson;
import com.example.tgBot.repository.LessonRepository;
import com.example.tgBot.repository.LessonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    @Autowired
    private LessonRepository repo;

    public List<Lesson> getLessons() {
        return repo.findAll();
    }
    @Transactional
    public void updateLessonHomework(String hw, String lesson) {
        Lesson lesson1 = repo.findByLessonName(lesson);
        lesson1.setHomework(hw);
        repo.save(lesson1);
    }

}
