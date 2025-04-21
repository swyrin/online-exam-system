package com.pdm.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Long ExamID;

    @Column(name = "bag_code")
    private String BagCode;

    @ManyToOne
    private Course Course;

    @ManyToOne
    private TestType ExamType;
      
    @JsonIgnore
    @ManyToMany(mappedBy = "AssignedExams")
    private List<Person> Attendees;
      
    @JsonIgnore
    @ManyToMany(mappedBy = "AssignedExams")
    private List<Room> Rooms;
}
