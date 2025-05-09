package com.pdm.backend.models.dto;

import java.util.List;
import java.util.Set;

import com.pdm.backend.models.Course;
import com.pdm.backend.models.Person;
import com.pdm.backend.models.Room;
import com.pdm.backend.models.TestType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamDto {

    private Long ExamID;
    private String BagCode;
    private Course Course;
   private TestType ExamType;
    private Set<Person> Attendees;
    private Set<Room> Rooms;


}
