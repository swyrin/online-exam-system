package com.pdm.backend.mappers.imple;


import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Course;
import com.pdm.backend.models.dto.CourseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component

public class CourseMapperImple implements Mapper<Course, CourseDto> {

    private final ModelMapper modelMapper;

    public CourseMapperImple(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseDto mapto(Course course) {
        return modelMapper.map(course, CourseDto.class);

    }

    @Override
    public Course mapfrom(CourseDto coursesdto) {
        return modelMapper.map(coursesdto, Course.class);
    }
}





