package com.pdm.backend.repositoriess;

import com.pdm.backend.models.ExamPersonReport;
import com.pdm.backend.models.composites.ExamPersonKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface ExamPersonReportRepository extends CrudRepository<ExamPersonReport, ExamPersonKey>,
        PagingAndSortingRepository<ExamPersonReport, ExamPersonKey> {
    @Query(value = "SELECT e FROM ExamPersonReport e WHERE e.key.CourseId= :course_id AND e.key.PersonID = :person_id")
    Optional<ExamPersonReport> findCourseAndPersonID(@Param("course_id") String course_id, @Param("person_id") String person_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE  FROM ExamPersonReport e WHERE e.key.CourseId =  :course_id AND e.key.PersonID = :person_id")
    void deleteCourseAndPersonID(@Param("course_id") String course_id, @Param("person_id") String person_id);
}
