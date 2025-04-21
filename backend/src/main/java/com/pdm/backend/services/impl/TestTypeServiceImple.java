package com.pdm.backend.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.aspectj.weaver.ast.Test;
import org.hibernate.service.JavaServiceLoadable;
import org.springframework.boot.autoconfigure.kafka.StreamsBuilderFactoryBeanCustomizer;
import org.springframework.stereotype.Service;

import com.pdm.backend.models.TestType;
import com.pdm.backend.repositoriess.TestTypeRepository;
import com.pdm.backend.services.TestTypeServices;

@Service
public class TestTypeServiceImple implements TestTypeServices{
      
    private TestTypeRepository testTypeRepository;
  

    public TestTypeServiceImple (TestTypeRepository testTypeRepository ){
        this.testTypeRepository = testTypeRepository;
      
    }

    @Override
    public TestType saveTestType(long TypeID , TestType testType)
    {
        testType.setTypeID(TypeID);
        return testTypeRepository.save(testType);
    }

    @Override 
    public List<TestType> getAllTestTypes(){
        return StreamSupport.stream(testTypeRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<TestType> findOne(long TypeID){
        return testTypeRepository.findById(TypeID);
    }

    @Override
    public boolean isExist(long TypeID){
        return testTypeRepository.existsById(TypeID);
    }

    @Override
    public TestType partialUpdated(long TypeID , TestType testType){

        testType.setTypeID(TypeID);

        return testTypeRepository.findById(TypeID).map(existingTestType ->{
           Optional.ofNullable(testType.getName()).ifPresent(existingTestType::setName);
           Optional.ofNullable(testType.getDescription()).ifPresent(existingTestType::setDescription);
           return testTypeRepository.save(existingTestType);
        }).orElseThrow(() -> new RuntimeException("No test type found"));
    }

    @Override
    public void delete (long TypeID){
        testTypeRepository.deleteById(TypeID);
    }
}


