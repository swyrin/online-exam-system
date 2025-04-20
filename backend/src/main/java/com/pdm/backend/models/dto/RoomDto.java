package com.pdm.backend.models.dto;

import java.util.List;

import com.pdm.backend.models.Course;
import com.pdm.backend.models.Exam;
import com.pdm.backend.models.Person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {
    public String RoomID;
    private Long headcount;
    private List<Person> Attendees;
    private List<Exam> AssignedExams;
    private List<Course> AssignedCourses;
}
