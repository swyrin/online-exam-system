package com.pdm.backend.models.dto;

import com.pdm.backend.enums.HumanGender;
import com.pdm.backend.models.Course;
import com.pdm.backend.models.Exam;
import com.pdm.backend.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class personDto {

    public String PersonID;
    public Date BirthDate;
    public String FirstName;
    public String MiddleName;
    public String LastName;
    public String Phone;
    public String Email;
    public Date JoinDate;
    public HumanGender Gender;
    public Role role;
    public List<Course> EnrolledCourses;
    private List<Exam> AssignedExams;


}
