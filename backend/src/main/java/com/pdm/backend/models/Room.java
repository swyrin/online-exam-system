package com.pdm.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Table(name = "rooms")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {
    @Id
    @Column(name = "room_id")
    public String RoomID;

    @Column(name = "headcount")
    private Long headcount;

    @ManyToMany
    @JoinTable(
            name = "person_room_attend",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> Attendees;

    @ManyToMany
    @JoinTable(
            name = "exam_room",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<Exam> AssignedExams;

    @ManyToMany
    @JoinTable(
            name = "course_room",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> AssignedCourses;
}
