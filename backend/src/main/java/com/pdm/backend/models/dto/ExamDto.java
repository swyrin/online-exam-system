package com.pdm.backend.models.dto;

import java.sql.Date;
import java.sql.Time;
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
    private String ExamID;
    private String BagCode;
    private String Status;
    private Date Date;
    private Time Time;
    private Short Difficulty;
    private Course Course;
    private TestType ExamType;
    private Set<Person> Attendees;
    private Set<Room> Rooms;
}
