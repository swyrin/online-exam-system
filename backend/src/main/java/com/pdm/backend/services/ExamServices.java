package com.pdm.backend.services;

import com.pdm.backend.models.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ExamServices {

    Exam saveExam( Exam exam);
    Optional<Exam> findOne(long exam_id);
    Boolean isExist(long exam_id);
    Page<Exam> findAll(Pageable pageable);
    void delete(long exam_id);


}
