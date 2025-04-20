package com.pdm.backend.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Exam;
import com.pdm.backend.models.dto.ExamDto;
import com.pdm.backend.services.ExamServices;

@RestController
public class ExamController {
     private Mapper<Exam , ExamDto> examMapper;
     private ExamServices examServices;

     public ExamController(Mapper<Exam, ExamDto> examMapper , ExamServices examServices){
        this.examMapper = examMapper;
        this.examServices = examServices;
     }

     @PutMapping(path ="/exams/{exam_id}")
     public ResponseEntity<ExamDto> createExam(@PathVariable("exam_id") long exam_id , @RequestBody ExamDto examDto){
          Exam exam = examMapper.mapfrom(examDto);
          Exam savedExam = examServices.saveExam(exam_id, exam);
          return new ResponseEntity<>(examMapper.mapto(savedExam) , HttpStatus.CREATED);
     }

     @GetMapping(path = "/exams")
     public List<ExamDto> getExamList(){
         List<Exam> exams = examServices.findAll();
         return exams.stream().map(examMapper::mapto).collect(Collectors.toList());
     }

     @GetMapping(path = "/exams/{exam_id}")
     public ResponseEntity<ExamDto> getExam(@PathVariable("exam_id") long exam_id , @RequestBody ExamDto examDto){
        boolean foundExamID = examServices.isExist(exam_id);
        if(!foundExamID){
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
         Exam exam = examMapper.mapfrom(examDto);
         Exam savedExam = examServices.saveExam(exam_id, exam);
         return new ResponseEntity<>(examMapper.mapto(savedExam) , HttpStatus.OK);
        }
     }

     @DeleteMapping(path = "/exams/{exam_id}")
     public ResponseEntity delete(@PathVariable("exam_id") long exam_id){
         examServices.delete(exam_id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
}
