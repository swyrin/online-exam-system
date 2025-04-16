package com.pdm.backend.models.dto;

import java.util.List;

import com.pdm.backend.models.Person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor

@Data
@Builder
@NoArgsConstructor

public class coursesDto {

    private Long CourseID;
    private String Name;
    private String Abbreviation;
    private List<Person> Attendees;

}
