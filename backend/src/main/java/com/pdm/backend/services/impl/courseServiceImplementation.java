package com.pdm.backend.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.stereotype.Service;

import com.pdm.backend.models.Course;
import com.pdm.backend.models.dto.coursesDto;
import com.pdm.backend.repositoriess.CourseRepository;
import com.pdm.backend.services.CourseServices;


@Service
public class courseServiceImplementation implements CourseServices {
     
    private CourseRepository courseRepository;

    public courseServiceImplementation(CourseRepository courseRepository){
       this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course){
      return courseRepository.save(course);

    }


    @Override 
    public List<Course> findAll(){
        return StreamSupport.stream(courseRepository.findAll().spliterator() , false).collect(Collectors.toList());
    }

    @Override 
    public Optional<Course> findOne(String course_id)
    {
      return courseRepository.findById(course_id);
    }

    @Override 
    public boolean isExist(String course_id){
       return courseRepository.existsById(course_id);
    }

    @Override 
    public Course partialUpdate(String course_id , Course courses){
        courses.setCourseID(course_id);

        return courseRepository.findById(course_id).map(existingCourse ->{
          Optional.ofNullable(courses.getName()).ifPresent(existingCourse::setName);
          Optional.ofNullable(courses.getAbbreviation()).ifPresent(existingCourse::setAbbreviation);
          return courseRepository.save(existingCourse);
        }).orElseThrow(() -> new RuntimeException ("Course do not exist"));
    }

    @Override
    public void delete(String course_id ){
       courseRepository.deleteById(course_id);
    }
    
}
