package com.pdm.backend.controllers;

import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.TestType;
import com.pdm.backend.models.dto.TestTypeDto;
import com.pdm.backend.services.TestTypeServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TestTypeController {

    private final TestTypeServices testTypeServices;
    private final Mapper<TestType, TestTypeDto> typeMapper;

    public TestTypeController(TestTypeServices testTypeServices, Mapper<TestType, TestTypeDto> testMapper) {
        this.testTypeServices = testTypeServices;
        this.typeMapper = testMapper;

    }

    @PutMapping(path = "/types/{type_id}")
    public ResponseEntity<TestTypeDto> CreatedTestType(@PathVariable("type_id") long type_id, @RequestBody TestTypeDto testTypeDto) {

        TestType testType = typeMapper.mapfrom(testTypeDto);
        Boolean foundTestTypeID = testTypeServices.isExist(type_id);

        TestType savedTestType = testTypeServices.saveTestType(type_id, testType);
        TestTypeDto savedTestTypeDto = typeMapper.mapto(savedTestType);

        if (foundTestTypeID) {
            return new ResponseEntity<>(savedTestTypeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedTestTypeDto, HttpStatus.CREATED);
        }

    }

    @GetMapping(path = "/types")
    public List<TestTypeDto> GetTypeList() {

        List<TestType> result = testTypeServices.getAllTestTypes();
        return result.stream().map(typeMapper::mapto).collect(Collectors.toList());
    }

    @GetMapping(path = "/types/{type_id}")
    public ResponseEntity<TestTypeDto> GetTypeID(@PathVariable("type_id") long type_id, @RequestBody TestTypeDto testTypeDto) {

        boolean found = testTypeServices.isExist(type_id);
        if (!found) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }


    }


    @PatchMapping(path = "/types/{type_id}")
    public ResponseEntity<TestTypeDto> partialUpdate(@PathVariable("type_id") long type_id, @RequestBody TestTypeDto testTypeDto) {

        Optional<TestType> result = testTypeServices.findOne(type_id);
        if (!result.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        testTypeDto.setTypeID(type_id);
        TestType testTypeEn = typeMapper.mapfrom(testTypeDto);
        TestType partialUpdatedTestType = testTypeServices.partialUpdated(type_id, testTypeEn);
        return new ResponseEntity<>(typeMapper.mapto(partialUpdatedTestType), HttpStatus.OK);
    }


    @DeleteMapping(path = "/types/{type_id}")
    public ResponseEntity delete(@PathVariable("type_id") long type_id) {
        testTypeServices.delete(type_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
