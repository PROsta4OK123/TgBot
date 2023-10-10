package com.example.tgBot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
@Entity
@Table
public class WorkDay {
    public WorkDay() {
    }

    @Id
    @Column()
    @GeneratedValue(generator = "increment")
    private Long id;

    @Column
    @NonNull
    private String dayName;
}
