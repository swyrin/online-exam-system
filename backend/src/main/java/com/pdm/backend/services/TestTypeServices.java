package com.pdm.backend.services;

import java.util.List;
import java.util.Optional;

import com.pdm.backend.models.TestType;

public interface TestTypeServices {

    TestType saveTestType(long TypeID , TestType testType);
    List<TestType> getAllTestTypes();
    Optional<TestType> findOne(long TypeID);
    boolean isExist(long TypeID);
    TestType partialUpdated(long TypeID , TestType testType);
    void delete(long TypeID);


      
}
