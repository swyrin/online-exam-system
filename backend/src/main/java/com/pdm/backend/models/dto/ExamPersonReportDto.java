package com.pdm.backend.models.dto;

import com.pdm.backend.models.Course;
import com.pdm.backend.models.Person;
import com.pdm.backend.models.composites.ExamPersonKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamPersonReportDto {

    private ExamPersonKey key;

    private Person person;
    private Course course;
    private Integer Grade;
    private Date IssueDate;
}
