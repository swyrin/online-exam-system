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

@RestController
public class ExamController {
    private final Mapper<Exam, ExamDto> examMapper;
    private final ExamServices examServices;

    public ExamController(Mapper<Exam, ExamDto> examMapper, ExamServices examServices) {
        this.examMapper = examMapper;
        this.examServices = examServices;
    }

    @PutMapping(path = "/exams/{exam_id}")
    public ResponseEntity<ExamDto> createExam(@PathVariable("exam_id") long exam_id, @RequestBody ExamDto examDto) {
        Exam exam = examMapper.mapfrom(examDto);
        Exam savedExam = examServices.saveExam(exam_id, exam);
        return new ResponseEntity<>(examMapper.mapto(savedExam), HttpStatus.CREATED);
    }

    @GetMapping(path = "/exams")
    public Page<ExamDto> listExams(Pageable pageable) {
        Page<Exam> exams = examServices.findAll(pageable);
        return exams.map(examMapper::mapto);
    }

    @GetMapping(path = "/exams/{exam_id}")
    public ResponseEntity<ExamDto> getExam(@PathVariable("exam_id") long exam_id, @RequestBody ExamDto examDto) {
        boolean foundExamID = examServices.isExist(exam_id);
        if (!foundExamID) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Exam exam = examMapper.mapfrom(examDto);
            Exam savedExam = examServices.saveExam(exam_id, exam);
            return new ResponseEntity<>(examMapper.mapto(savedExam), HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/exams/{exam_id}")
    public ResponseEntity<ExamDto> delete(@PathVariable("exam_id") long exam_id) {
        examServices.delete(exam_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
