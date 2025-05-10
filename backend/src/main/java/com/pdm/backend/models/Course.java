package com.pdm.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @Column(name = "course_id")
    private String CourseID;

    @Column(name = "name")
    private String Name;

    @Column(name = "abbreviation")
    private String Abbreviation;

    @JsonIgnore
    @ManyToMany(mappedBy = "EnrolledCourses")
    private Set<Person> Attendees;

    @JsonIgnore
    @ManyToMany(mappedBy = "AssignedCourses")
    private Set<Room> roomAssigned;
     
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<ExamPersonReport> courseReports;

}
