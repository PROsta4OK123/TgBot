package com.example.tgBot.repository;

import com.example.tgBot.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("SELECT l.lessonName FROM Lesson l")
    List<String> findAllLessonName();
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Lesson u WHERE u.lessonName = ?1")
    boolean existByLessonName(String lessonName);
    Lesson findByLessonName(String lessonName);
}
