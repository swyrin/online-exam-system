package com.pdm.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pdm.backend.models.Course;

public interface CourseServices {

    Course createCourse(String course_id ,Course course);
    
    Page<Course> findAll(Pageable pageable);
    Optional<Course> findOne(String course_id);
    boolean isExist(String course_id);

    Course partialUpdate(String course_id , Course courses);
    void delete(String course_id);
    Course setCourseReports(String person_id , String course_id);
}
