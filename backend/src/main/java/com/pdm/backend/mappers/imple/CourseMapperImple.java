package com.pdm.backend.mappers.imple;



import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pdm.backend.models.Course;
import com.pdm.backend.models.dto.coursesDto;
import com.pdm.backend.mappers.Mapper;


@Component

public class CourseMapperImple implements Mapper<Course , coursesDto> {

    private ModelMapper modelMapper ; 

    public CourseMapperImple(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override 
    public coursesDto mapto (Course course){
           return modelMapper.map( course , coursesDto.class);

    }

    @Override
    public Course mapfrom (coursesDto coursesdto){
        return modelMapper.map(coursesdto , Course.class);
    }
}





