package com.pdm.backend.repositoriess;

import com.pdm.backend.models.Exam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExamRepository extends CrudRepository<Exam, Long>, PagingAndSortingRepository<Exam, Long> {


}
