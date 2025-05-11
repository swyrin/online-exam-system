package com.pdm.backend.models.dto;

import java.util.Date;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pdm.backend.enums.HumanGender;
import com.pdm.backend.models.Course;
import com.pdm.backend.models.Exam;
import com.pdm.backend.models.ExamPersonReport;
import com.pdm.backend.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDto {
    public String PersonID;
    public Date BirthDate;
    public String FirstName;
    public String MiddleName;
    public String LastName;
    public String Phone;
    public String Email;
    public Date JoinDate;
    public HumanGender Gender;

//    public Set<Course> EnrolledCourses;
//    private Set<Exam> AssignedExams;
//    private Set<ExamPersonReport> personReports;
}
