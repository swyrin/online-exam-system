package com.pdm.backend.services;

import com.pdm.backend.models.TestType;

import java.util.List;
import java.util.Optional;

public interface TestTypeServices {

    TestType saveTestType(long TypeID, TestType testType);
    List<TestType> getAllTestTypes();
    Optional<TestType> findOne(long TypeID);
    boolean isExist(long TypeID);
    TestType partialUpdated(long TypeID, TestType testType);
    void delete(long TypeID);


}
