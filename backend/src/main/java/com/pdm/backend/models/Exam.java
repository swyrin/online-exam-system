package com.pdm.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.*;
import java.util.Set;

@Entity
@Builder
@Table(name = "exams")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exam {
    @Id
    
    @Column(name = "exam_id")
    private String ExamID;

    @Column(name = "date")
    private Date Date;

    @Column(name = "time")
    private Time Time;

    @Column(name = "difficulty")
    private Short Difficulty;

    @Column(name = "bag_code")
    private String BagCode;

    @Column(name = "status")
    private String Status;

    @ManyToOne
    private Course Course;

    @ManyToOne
    private TestType ExamType;

    @JsonIgnore
    @ManyToMany(mappedBy = "AssignedExams")
    private Set<Person> Attendees;

    @JsonIgnore
    @ManyToMany(mappedBy = "AssignedExams")
    private Set<Room> Rooms;
}
