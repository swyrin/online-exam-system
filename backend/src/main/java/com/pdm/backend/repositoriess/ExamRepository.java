package com.pdm.backend.repositoriess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pdm.backend.models.Exam;

public interface ExamRepository extends CrudRepository<Exam , Long> , PagingAndSortingRepository <Exam , Long>
{

    


}
