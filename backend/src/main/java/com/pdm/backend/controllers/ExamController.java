package com.pdm.backend.controllers;


import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Exam;
import com.pdm.backend.models.dto.ExamDto;
import com.pdm.backend.services.ExamServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ExamController {
    private final Mapper<Exam, ExamDto> examMapper;
    private final ExamServices examServices;

    public ExamController(Mapper<Exam, ExamDto> examMapper, ExamServices examServices) {
        this.examMapper = examMapper;
        this.examServices = examServices;
    }

    @PostMapping(path = "/exams")
    public ResponseEntity<ExamDto> createExam(@RequestBody ExamDto examDto){
           Exam exam = examMapper.mapfrom(examDto);
           if (examServices.isExist(exam.getExamID())) {
          throw new ResponseStatusException(HttpStatus.CONFLICT, "An exam with this ID already exists.");
        }
           Exam createdExam = examServices.saveExam( exam);
           return new ResponseEntity<>(examMapper.mapto(createdExam) , HttpStatus.CREATED);
    }

    @PutMapping(path = "/exams/{exam_id}")
    public ResponseEntity<ExamDto> UpdateExam(@PathVariable("exam_id") long exam_id, @RequestBody ExamDto examDto) {
        Exam exam = examMapper.mapfrom(examDto);
        boolean isExamExist = examServices.isExist(exam_id);
        
      if(isExamExist){
        examDto.setExamID(exam_id);
         Exam savedExam = examServices.saveExam( exam);
        ExamDto savedExamDto = examMapper.mapto(savedExam);
         return new ResponseEntity<>(savedExamDto , HttpStatus.OK);
      }
      else{
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
        
            
        
        
    }

    @GetMapping(path = "/exams")
    public Page<ExamDto> listExams(Pageable pageable) {
        Page<Exam> exams = examServices.findAll(pageable);
        return exams.map(examMapper::mapto);
    }

    @GetMapping(path = "/exams/{exam_id}")
    public ResponseEntity<ExamDto> getExam(@PathVariable("exam_id") long exam_id,  ExamDto examDto) {
        boolean foundExamID = examServices.isExist(exam_id);
        if (!foundExamID) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Exam exam = examMapper.mapfrom(examDto);
            Exam savedExam = examServices.saveExam( exam);
            return new ResponseEntity<>(examMapper.mapto(savedExam), HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/exams/{exam_id}")
    public ResponseEntity<ExamDto> delete(@PathVariable("exam_id") long exam_id) {
        examServices.delete(exam_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
