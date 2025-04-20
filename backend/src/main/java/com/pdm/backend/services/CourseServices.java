package com.pdm.backend.services;

import java.util.List;
import java.util.Optional;

import com.pdm.backend.models.Course;

public interface CourseServices {

    Course createCourse(Course course);
    
    List<Course> findAll();
    Optional<Course> findOne(String course_id);
    boolean isExist(String course_id);

    Course partialUpdate(String course_id , Course courses);
    void delete(String course_id);
}
