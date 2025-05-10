package com.pdm.backend.services.impl;

import com.pdm.backend.models.Course;
import com.pdm.backend.repositoriess.CourseRepository;
import com.pdm.backend.services.CourseServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;



@Service
public class courseServiceImplementation implements CourseServices {

    private final CourseRepository courseRepository;

    public courseServiceImplementation(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(String course_id, Course course) {
        course.setCourseID(course_id);
        return courseRepository.save(course);

    }


    @Override 
    public Page<Course> findAll(Pageable pageable){
        return courseRepository.findAll(pageable);
    }

    @Override
    public Optional<Course> findOne(String course_id) {
        return courseRepository.findById(course_id);
    }

    @Override
    public boolean isExist(String course_id) {
        return courseRepository.existsById(course_id);
    }

    @Override
    public Course partialUpdate(String course_id, Course courses) {
        courses.setCourseID(course_id);

        return courseRepository.findById(course_id).map(existingCourse -> {
            Optional.ofNullable(courses.getName()).ifPresent(existingCourse::setName);
            Optional.ofNullable(courses.getAbbreviation()).ifPresent(existingCourse::setAbbreviation);
            return courseRepository.save(existingCourse);
        }).orElseThrow(() -> new RuntimeException("Course do not exist"));
    }

    @Override
    public void delete(String course_id) {
        courseRepository.deleteById(course_id);
    }

    @Override
    public Course setCourseReports(String person_id, String course_id) {
        return null;
    }

}
