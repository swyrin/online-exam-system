package com.pdm.backend.services;

import com.pdm.backend.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseServices {

    Course createCourse(String course_id, Course course);

    List<Course> findAll();

    Optional<Course> findOne(String course_id);

    boolean isExist(String course_id);

    Course partialUpdate(String course_id, Course courses);

    void delete(String course_id);

    Course setCourseReports(String person_id, String course_id);
}
