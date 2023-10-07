package com.example.tgBot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
@Entity
@Table
public class Lesson {
    public Lesson() {
    }

    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(generator = "increment")
    private Long lessonId;

    @Column
    @NonNull
    private String lessonName;

    @Column
    private String homework;
}
