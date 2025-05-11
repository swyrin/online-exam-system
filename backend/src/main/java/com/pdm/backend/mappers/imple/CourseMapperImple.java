package com.pdm.backend.mappers.imple;


import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Course;
import com.pdm.backend.models.dto.CoursesDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component

public class CourseMapperImple implements Mapper<Course, CoursesDto> {

    private final ModelMapper modelMapper;

    public CourseMapperImple(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CoursesDto mapto(Course course) {
        return modelMapper.map(course, CoursesDto.class);

    }

    @Override
    public Course mapfrom(CoursesDto coursesdto) {
        return modelMapper.map(coursesdto, Course.class);
    }
}





