package com.example.tgBot.repository;

import com.example.tgBot.entity.WorkDayMatching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkDayMatchingRepository extends JpaRepository<WorkDayMatching,Long> {
    @Query("SELECT wdm FROM WorkDayMatching wdm WHERE wdm.workDay.id = :workDayId")
    List<WorkDayMatching> findAllByWorkDayId(Long workDayId);
}

