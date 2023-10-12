package com.example.tgBot.controller;

import com.example.tgBot.entity.Lesson;
import com.example.tgBot.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @GetMapping("/get-lessons")
    public List<Lesson> getLessons(){
        return lessonService.getLessons();
    }
    @PostMapping("/add-lesson")
    public void addLesson(@RequestBody Lesson lesson){
        lessonService.addLesson(lesson);
    }
    @DeleteMapping("/delete-lesson/{id}")
    public void deleteById(@PathVariable Long id){
        lessonService.deleteLesson(id);
    }

}
