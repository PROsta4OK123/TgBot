package com.example.tgBot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Entity
@Table
@Data
public class WorkDayMatching {
    public WorkDayMatching() {
    }

    @Id
    @GeneratedValue(generator = "increment")
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name="lesson_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name ="work_day_id")
    private WorkDay workDay;
}
