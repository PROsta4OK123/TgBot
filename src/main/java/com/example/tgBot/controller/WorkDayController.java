package com.example.tgBot.controller;

import com.example.tgBot.entity.WorkDay;
import com.example.tgBot.service.WorkDayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkDayController {
    private final WorkDayService workDayService;

    public WorkDayController(WorkDayService workDayService) {
        this.workDayService = workDayService;
    }
    @GetMapping("/get-work-days")
    public List<WorkDay> getWorkDays(){
        return workDayService.getWorkDays();
    }
    @PostMapping("/add-work-day")
    public void addWorkDay(WorkDay workDay){
        workDayService.addWorkDay(workDay);
    }
}
