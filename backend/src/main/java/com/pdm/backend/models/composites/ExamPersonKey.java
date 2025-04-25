package com.pdm.backend.models.composites;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ExamPersonKey implements Serializable {
    @Column(name = "person_id")
   private String PersonID;

    @Column(name = "course_id")
   private String CourseId;
}