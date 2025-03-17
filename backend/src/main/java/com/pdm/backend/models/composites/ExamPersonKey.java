package com.pdm.backend.models.composites;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ExamPersonKey implements Serializable {
    @Column(name = "person_id")
    Long PersonID;

    @Column(name = "course_id")
    Long CourseId;
}