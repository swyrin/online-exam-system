package com.pdm.backend.models;

import com.pdm.backend.enums.HumanGender;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Builder
@Table(name = "persons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @Column(name = "person_id")
    public String PersonID;

    @Column(name = "birth_date")
    public Date BirthDate;

    @Column(name = "first_name")
    public String FirstName;

    @Column(name = "middle_name")
    public String MiddleName;

    @Column(name = "last_name")
    public String LastName;

    @Column(name = "phone")
    public String Phone;

    @Column(name = "email")
    public String Email;

    @Column(name = "join_date")
    public Date JoinDate;

    @Column(name = "gender")
    public HumanGender Gender;

    @OneToOne
    @JoinColumn(name = "role", referencedColumnName = "role_id")
    public Role Role;

    @ManyToMany
    @JoinTable(
            name = "course_person",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    public List<Course> EnrolledCourses;

    @ManyToMany
    @JoinTable(
            name = "person_exam",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<Exam> AssignedExams;
}
