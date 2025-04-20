package com.pdm.backend.repositoriess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pdm.backend.models.TestType;

@Repository
public interface TestTypeRepository extends CrudRepository < TestType , Long> {
         
}
