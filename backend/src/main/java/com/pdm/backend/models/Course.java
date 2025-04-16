package com.pdm.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private long CourseID ;

    @Column(name = "name")
    private String Name;

    @Column(name = "abbreviation")
    private String Abbreviation;

    @ManyToMany(mappedBy = "EnrolledCourses")
    private List<Person> Attendees;

    
}
