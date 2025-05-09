package com.pdm.backend.services.impl;

import com.pdm.backend.models.ExamPersonReport;
import com.pdm.backend.models.composites.ExamPersonKey;
import com.pdm.backend.repositoriess.ExamPersonReportRepository;
import com.pdm.backend.services.ExamPersonReportServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ExamPersonReportServiceImple implements ExamPersonReportServices {
    private final ExamPersonReportRepository examPersonReportRepository;
    private final ExamPersonKey key;

    public ExamPersonReportServiceImple(ExamPersonReportRepository examPersonReportRepository) {
        this.examPersonReportRepository = examPersonReportRepository;
        this.key = new ExamPersonKey();
    }

    @Override
    public ExamPersonReport save(String course_id, String person_id, ExamPersonReport examPersonReport) {


        key.setCourseId(course_id);
        key.setPersonID(person_id);
        examPersonReport.setKey(key);
        return examPersonReportRepository.save(examPersonReport);


    }

    @Override
    public Optional<ExamPersonReport> find(String course_id, String person_id) {

        return examPersonReportRepository.findCourseAndPersonID(course_id, person_id);


    }


    @Override
    public Page<ExamPersonReport> List(Pageable pageable) {
        return examPersonReportRepository.findAll(pageable);
    }

    @Override
    public boolean isExist(String course_id, String person_id) {


        boolean foundExistCourse = key.getCourseId().equals(course_id);
        boolean foundExistPerson = key.getPersonID().equals(person_id);
        if (foundExistCourse && foundExistPerson) {
            return examPersonReportRepository.existsById(key);
        } else {
            return false;
        }


    }


    @Override
    public void delete(String person_id, String course_id) {

        examPersonReportRepository.deleteCourseAndPersonID(person_id, course_id);
    }
}
