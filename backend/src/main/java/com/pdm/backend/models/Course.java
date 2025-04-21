package com.pdm.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private List<Person> Attendees;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "AssignedCourses")
    private List<Room> roomAssigned;
    
}
