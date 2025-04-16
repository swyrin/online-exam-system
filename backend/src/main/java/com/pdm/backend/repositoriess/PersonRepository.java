package com.pdm.backend.repositoriess;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pdm.backend.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, String>{

    // Iterable<Person> FindAgeLess(int age);

    // @Query("SELECT p FROM person p WHERE p.age >= ?1")
    // Iterable<Person> FindTheOldestAge(int age);
   
}
