package com.pdm.backend.controllers;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pdm.backend.models.Course;
import com.pdm.backend.models.dto.coursesDto;
import com.pdm.backend.services.CourseServices;

import com.pdm.backend.mappers.Mapper;

@RestController
public class CourseController {
    
    

    private CourseServices courseServices ; 
    private Mapper<Course , coursesDto> courseMapper ;

   

    public CourseController(CourseServices courseServices  , Mapper<Course , coursesDto> courseMapper ){
        this.courseServices = courseServices;
        this.courseMapper = courseMapper;
    }

   @PutMapping(path = "/courses/{course_id}")
    public ResponseEntity<coursesDto> createCourse(@PathVariable("course_id") long course_id, @RequestBody coursesDto course)
    {
           Course courseEntity   = courseMapper.mapfrom (course);
           Course createdCourse = courseServices.createCourse(courseEntity);
           return new ResponseEntity<>(courseMapper.mapto(createdCourse) , HttpStatus.CREATED);
    }

    @GetMapping("/courses")
    public List<coursesDto> coursesList(){
        List<Course> courses = courseServices.findAll();
        return courses.stream().map(courseMapper::mapto).collect(Collectors.toList());
    }

    @GetMapping(path = "/courses/{course_id}")
    public ResponseEntity<coursesDto> getCourse(@PathVariable("course_id") long course_id , coursesDto coursesDto ){

        
        Optional<Course> foundCourse= courseServices.findOne(course_id);
       return  foundCourse.map(course ->{
              coursesDto coursedto = courseMapper.mapto(course);
              return new ResponseEntity<>(coursedto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "/courses/{course_id}")
    public ResponseEntity<coursesDto> fullUpdate(@PathVariable("course_id") Long course_id , @RequestBody  coursesDto course)
    {
           if(!courseServices.isExist(course_id)){
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }

           course.setCourseID(course_id);
           Course courseEntity = courseMapper.mapfrom(course);
           Course savedCourse = courseServices.createCourse(courseEntity);
           coursesDto coursedto = courseMapper.mapto(savedCourse);
           return new ResponseEntity<>(coursedto , HttpStatus.OK);
    }

    @PatchMapping(path = "/courses/ {course_id}")
    public ResponseEntity<coursesDto> partialUpdate(@PathVariable("course_id") long course_id , @RequestBody coursesDto coursedto)
    {

        if(!courseServices.isExist(course_id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
           coursedto.setCourseID(course_id);
           Course courseEntity = courseMapper.mapfrom(coursedto);
           
           Course savedCourse = courseServices.partialUpdate(course_id , courseEntity);
           return new ResponseEntity<>(courseMapper.mapto(savedCourse) , HttpStatus.OK);


    }

    @DeleteMapping(path= "/courses/{course_id}")
    public ResponseEntity<coursesDto> delete(@PathVariable("course_id") long course_id)
    {
        courseServices.delete(course_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }








}
