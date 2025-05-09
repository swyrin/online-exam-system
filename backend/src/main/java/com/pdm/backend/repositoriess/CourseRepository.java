package com.pdm.backend.repositoriess;

import com.pdm.backend.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CourseRepository extends CrudRepository<Course,String> , PagingAndSortingRepository<Course,String>{

    // Iterable<Course> CourseIdlessthan(long CourseID);
    // @Query ("SELECT a from Course a where a.age > ?1 ")
    // Iterable<Course>  findcourseWithGreaterIDThan(long courseID);


}
