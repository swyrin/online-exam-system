package com.pdm.backend.services;

import java.util.List;
import java.util.Optional;

import com.pdm.backend.models.Course;

public interface CourseServices {

    Course createCourse(Course course);
    
    List<Course> findAll();
    Optional<Course> findOne(long course_id);
    boolean isExist(long course_id);

    Course partialUpdate(long course_id , Course courses);
    void delete(long course_id);
}
