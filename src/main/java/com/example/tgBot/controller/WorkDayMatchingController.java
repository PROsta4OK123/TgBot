package com.example.tgBot.controller;

import com.example.tgBot.entity.Lesson;
import com.example.tgBot.entity.WorkDayMatching;
import com.example.tgBot.repository.WorkDayMatchingRepository;
import com.example.tgBot.service.WorkDayMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkDayMatchingController {

    private final WorkDayMatchingRepository workDayMatchingRepository;
    private final WorkDayMatchingService workDayMatchingService;
    @Autowired
    public WorkDayMatchingController(WorkDayMatchingRepository workDayMatchingRepository, WorkDayMatchingService workDayMatchingService) {
        this.workDayMatchingRepository = workDayMatchingRepository;
        this.workDayMatchingService = workDayMatchingService;
    }

    @GetMapping("/get-workDayMatching")
    public List<Lesson> getLessons(){
        return workDayMatchingRepository.findAllByWorkDayId(1L).stream()
                .map(WorkDayMatching::getLesson)
                .toList();
    }
    @PostMapping("/add-work-day-matching")
    public void addWorkDayMatching(@RequestBody WorkDayMatching workDayMatching){
        workDayMatchingService.addWorkDayMatching(workDayMatching);
    }
    @GetMapping("/get-mapping")
    public void getMatching(){
        workDayMatchingService.getWorkDayMatchings();
    }
}
