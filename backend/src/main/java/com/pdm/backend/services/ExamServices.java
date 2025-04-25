package com.pdm.backend.services;

import java.util.List;
import java.util.Optional;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pdm.backend.models.Exam;

public interface ExamServices {

    Exam saveExam(long exam_id , Exam exam);
    Optional<Exam> findOne(long exam_id);
    Boolean isExist(long exam_id);
     Page<Exam> findAll (Pageable pageable);
    
    void delete(long exam_id);



    
}
