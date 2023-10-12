package com.example.tgBot.service;

import com.example.tgBot.entity.WorkDay;
import com.example.tgBot.repository.WorkDayMatchingRepository;
import com.example.tgBot.repository.WorkDayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkDayService {
    private final WorkDayRepository repository;

    public WorkDayService(WorkDayRepository repository) {
        this.repository = repository;
    }

    public void addWorkDay(WorkDay workDay){
        repository.save(workDay);
    }
    public List<WorkDay> getWorkDays(){
        return repository.findAll();
    }
    public void deleteDay(Long id){
        repository.deleteById(id);
    }
}
