package com.pdm.backend.models.dto;

import com.pdm.backend.models.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor

@Data
@Builder
@NoArgsConstructor

public class coursesDto {

    private String CourseID;
    private String Name;
    private String Abbreviation;
    private List<Person> Attendees;

}
