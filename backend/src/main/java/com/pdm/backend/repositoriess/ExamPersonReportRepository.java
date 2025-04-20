package com.pdm.backend.repositoriess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pdm.backend.models.ExamPersonReport;
import com.pdm.backend.models.composites.ExamPersonKey;

@Repository
public interface ExamPersonReportRepository  extends CrudRepository<ExamPersonReport , ExamPersonKey>{
        
}
