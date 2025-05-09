package com.pdm.backend.services;

import com.pdm.backend.models.ExamPersonReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ExamPersonReportServices {

    ExamPersonReport save(String course_id, String person_id, ExamPersonReport examPersonReport);

    Optional<ExamPersonReport> find(String course_id, String person_id);

    Page<ExamPersonReport> List(Pageable pageable);

    boolean isExist(String course_id, String person_id);

    void delete(String course_id, String person_id);


}
