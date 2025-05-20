package com.pdm.backend.controllers;

import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Course;
import com.pdm.backend.models.dto.CoursesDto;
import com.pdm.backend.services.CourseServices;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class CourseController {


    private final CourseServices courseServices;
    private final Mapper<Course, CoursesDto> courseMapper;


    public CourseController(CourseServices courseServices, Mapper<Course, CoursesDto> courseMapper) {
        this.courseServices = courseServices;
        this.courseMapper = courseMapper;
    }

    @PutMapping(path = "/courses/{course_id}")
    public ResponseEntity<CoursesDto> createCourse(@PathVariable("course_id") String course_id, @RequestBody CoursesDto course) {
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
    public List<CoursesDto> courseList(Pageable pageable){
        Page<Course> course = courseServices.findAll(pageable);
      return course.getContent().stream().map(courseMapper::mapto).collect(Collectors.toList());
    }

    @GetMapping(path = "/courses/{course_id}")
    public ResponseEntity<CoursesDto> getCourse(@PathVariable("course_id") String course_id,@RequestBody CoursesDto CoursesDto) {


        Optional<Course> foundCourse = courseServices.findOne(course_id);
        return foundCourse.map(course -> {
            CoursesDto coursedto = courseMapper.mapto(course);
            return new ResponseEntity<>(coursedto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


    @PatchMapping(path = "/courses/ {course_id}")
    public ResponseEntity<CoursesDto> partialUpdate(@PathVariable("course_id") String course_id, @RequestBody CoursesDto coursedto) {

        if (!courseServices.isExist(course_id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        coursedto.setCourseID(course_id);
        Course courseEntity = courseMapper.mapfrom(coursedto);

        Course savedCourse = courseServices.partialUpdate(course_id, courseEntity);
        return new ResponseEntity<>(courseMapper.mapto(savedCourse), HttpStatus.OK);


    }

    @DeleteMapping(path = "/courses/{course_id}")
    public ResponseEntity<CoursesDto> delete(@PathVariable("course_id") String course_id) {
        courseServices.delete(course_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
