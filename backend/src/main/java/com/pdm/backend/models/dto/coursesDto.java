package com.pdm.backend.models.dto;

import java.util.List;
import java.util.Set;

import com.pdm.backend.models.ExamPersonReport;
import com.pdm.backend.models.Person;
import com.pdm.backend.models.Room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor

@Data
@Builder
@NoArgsConstructor

public class coursesDto {

    private String CourseID;
    private String Name;
    private String Abbreviation;
    private Set<Person> Attendees;
    private Set<Room> roomAssigned;
    private Set<ExamPersonReport> courseReports ;

}
