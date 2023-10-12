package com.example.tgBot.controller;

import com.example.tgBot.entity.WorkDay;
import com.example.tgBot.service.WorkDayService;
import org.springframework.web.bind.annotation.*;

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
    public void addWorkDay(@RequestBody WorkDay workDay){
        workDayService.addWorkDay(workDay);
    }
    @DeleteMapping("/delete-work-day/{id}")
    public void deleteWorkDay(@PathVariable Long id){
        workDayService.deleteDay(id);
    }
}
