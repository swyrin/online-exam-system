package com.pdm.backend.mappers.imple;

import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Exam;
import com.pdm.backend.models.dto.ExamDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ExamMapperImple implements Mapper<Exam, ExamDto> {
    private final ModelMapper modelMapper;

    public ExamMapperImple(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public Exam mapfrom(ExamDto examDto) {
        return modelMapper.map(examDto, Exam.class);
    }

    @Override
    public ExamDto mapto(Exam exam) {
        return modelMapper.map(exam, ExamDto.class);
    }
}
    

