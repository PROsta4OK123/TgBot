package com.example.tgBot.repository;

import com.example.tgBot.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("SELECT l FROM Lesson l " +
            "INNER JOIN WorkDayMatching wm ON l.lesson_id = wm.lesson_id " +
            "WHERE wm.work_day_id = :workDayId")
    LinkedList<Lesson> findLessonsByWorkDayId(@Param("workDayId") Long workDayId);
}
