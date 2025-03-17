package com.pdm.backend.models;

import com.pdm.backend.models.composites.ExamPersonKey;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Table(name = "person_exam_report")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExamPersonReport {
    @EmbeddedId
    private ExamPersonKey key;

    @ManyToOne
    @MapsId("PersonID")
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @MapsId("CourseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "grade")
    private Integer Grade;

    @Column(name = "issue_date")
    private Date IssueDate;
}
