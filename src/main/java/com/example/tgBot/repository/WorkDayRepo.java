package com.example.tgBot.repository;

import com.example.tgBot.entity.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkDayRepo extends JpaRepository<WorkDay,Long> {
}
