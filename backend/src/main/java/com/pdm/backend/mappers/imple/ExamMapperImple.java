package com.pdm.backend.mappers.imple;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Exam;
import com.pdm.backend.models.dto.ExamDto;


@Component
public class ExamMapperImple implements Mapper<Exam , ExamDto>{
   private ModelMapper modelMapper;

   public ExamMapperImple(ModelMapper modelMapper){
    this.modelMapper  =modelMapper;
   }

   @Override 
   public Exam mapfrom(ExamDto examDto){
     return modelMapper.map(examDto, Exam.class);
   }

   @Override
   public ExamDto mapto(Exam exam){
     return modelMapper.map(exam, ExamDto.class);
   }
}
    

