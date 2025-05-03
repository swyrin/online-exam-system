package com.pdm.backend.controllers;

import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Course;
import com.pdm.backend.models.dto.coursesDto;
import com.pdm.backend.services.CourseServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CourseController {


    private final CourseServices courseServices;
    private final Mapper<Course, coursesDto> courseMapper;


    public CourseController(CourseServices courseServices, Mapper<Course, coursesDto> courseMapper) {
        this.courseServices = courseServices;
        this.courseMapper = courseMapper;
    }

    @PutMapping(path = "/courses/{course_id}")
    public ResponseEntity<coursesDto> createCourse(@PathVariable("course_id") String course_id, @RequestBody coursesDto course) {
        Course courseEntity = courseMapper.mapfrom(course);
        boolean foundCourseID = courseServices.isExist(course_id);
        if (!foundCourseID) {
            Course createdCourse = courseServices.createCourse(course_id, courseEntity);
            return new ResponseEntity<>(courseMapper.mapto(createdCourse), HttpStatus.CREATED);
        } else {
            course.setCourseID(course_id);
            Course updatedCourse = courseServices.createCourse(course_id, courseEntity);
            return new ResponseEntity<>(courseMapper.mapto(updatedCourse), HttpStatus.OK);
        }
    }

    @GetMapping("/courses")
    public List<coursesDto> coursesList() {
        List<Course> courses = courseServices.findAll();
        return courses.stream().map(courseMapper::mapto).collect(Collectors.toList());
    }

    @GetMapping(path = "/courses/{course_id}")
    public ResponseEntity<coursesDto> getCourse(@PathVariable("course_id") String course_id, coursesDto coursesDto) {


        Optional<Course> foundCourse = courseServices.findOne(course_id);
        return foundCourse.map(course -> {
            coursesDto coursedto = courseMapper.mapto(course);
            return new ResponseEntity<>(coursedto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


    @PatchMapping(path = "/courses/ {course_id}")
    public ResponseEntity<coursesDto> partialUpdate(@PathVariable("course_id") String course_id, @RequestBody coursesDto coursedto) {

        if (!courseServices.isExist(course_id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        coursedto.setCourseID(course_id);
        Course courseEntity = courseMapper.mapfrom(coursedto);

        Course savedCourse = courseServices.partialUpdate(course_id, courseEntity);
        return new ResponseEntity<>(courseMapper.mapto(savedCourse), HttpStatus.OK);


    }

    @DeleteMapping(path = "/courses/{course_id}")
    public ResponseEntity<coursesDto> delete(@PathVariable("course_id") String course_id) {
        courseServices.delete(course_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
