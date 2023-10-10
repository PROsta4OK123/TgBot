package com.example.tgBot.controller;

import com.example.tgBot.entity.Lesson;
import com.example.tgBot.entity.WorkDayMatching;
import com.example.tgBot.repository.WorkDayMatchingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WorkDayMatchingController {
    @Autowired
    WorkDayMatchingRepository workDayMatchingRepository;
    @GetMapping("/get-workDayMatching")
    public List<Lesson> getLessons(){
        return workDayMatchingRepository.findAllByWorkDayId(1L).stream()
                .map(WorkDayMatching::getLesson)
                .toList();
    }
}
