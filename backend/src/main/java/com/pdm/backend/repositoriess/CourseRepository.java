package com.pdm.backend.repositoriess;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pdm.backend.models.Course;

@Repository

public interface CourseRepository extends CrudRepository<Course , Long>{

    // Iterable<Course> CourseIdlessthan(long CourseID);
    // @Query ("SELECT a from Course a where a.age > ?1 ")
    // Iterable<Course>  findcourseWithGreaterIDThan(long courseID);

   



}
