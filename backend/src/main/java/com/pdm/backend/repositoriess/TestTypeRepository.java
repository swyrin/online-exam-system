package com.pdm.backend.repositoriess;

import com.pdm.backend.models.TestType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTypeRepository extends CrudRepository<TestType, Long> {

}
