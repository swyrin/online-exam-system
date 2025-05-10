package com.pdm.backend.controllers;

import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.ExamPersonReport;
import com.pdm.backend.models.dto.ExamPersonReportDto;
import com.pdm.backend.services.ExamPersonReportServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ExamPersonReportController {

    private final ExamPersonReportServices examPersonReportServices;
    private final Mapper<ExamPersonReport, ExamPersonReportDto> reportMapper;

    public ExamPersonReportController(ExamPersonReportServices examPersonReportServices, Mapper<ExamPersonReport, ExamPersonReportDto> reportMapper) {
        this.examPersonReportServices = examPersonReportServices;
        this.reportMapper = reportMapper;
    }

    @PutMapping(path = "/ExamPersonReports/{person_id}/{course_id}")
    public ResponseEntity<ExamPersonReportDto> create(
            @PathVariable("person_id") String person_id,
            @PathVariable("course_id") String course_id,
            @RequestBody ExamPersonReportDto examPersonReportDto) {

        ExamPersonReport examPersonReport = reportMapper.mapfrom(examPersonReportDto);
        boolean foundEntity = examPersonReportServices.isExist(course_id, person_id);
        ExamPersonReport savedEntity = examPersonReportServices.save(course_id, person_id, examPersonReport);
        ExamPersonReportDto savedExamPersonReportDto = reportMapper.mapto(savedEntity);
        if (foundEntity) {
            return new ResponseEntity<>(savedExamPersonReportDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedExamPersonReportDto, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/ExamPersonReports/{person_id}/{course_id}")
    public ResponseEntity<ExamPersonReportDto> get(
            @PathVariable("person_id") String person_id,
            @PathVariable("course_id") String course_id,
            @RequestBody ExamPersonReportDto examPersonReportDto) {

        ExamPersonReport examPersonReport = reportMapper.mapfrom(examPersonReportDto);
        Optional<ExamPersonReport> result = examPersonReportServices.find(person_id, course_id);
        return result.map(examPersonReportEntity -> {
            ExamPersonReportDto savedExamPersonReportDto = reportMapper.mapto(examPersonReport);
            return new ResponseEntity<>(savedExamPersonReportDto, HttpStatus.OK);
        }).orElseThrow(() -> new RuntimeException("No PersonID and CourseID found"));

    }


    @GetMapping(path = "/ExamPersonReports")
    public Page<ExamPersonReportDto> List(Pageable pageable) {
        Page<ExamPersonReport> reportList = examPersonReportServices.List(pageable);
        return reportList.map(reportMapper::mapto);
    }


    @DeleteMapping(path = "/ExamPersonReports/{person_id}/{course_id}")
    public ResponseEntity<ExamPersonReportDto> deleteEntity(
            @PathVariable("person_id") String person_id,
            @PathVariable("course_id") String course_id
    ) {

        examPersonReportServices.delete(person_id, course_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
