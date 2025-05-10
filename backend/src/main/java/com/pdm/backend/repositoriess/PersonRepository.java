package com.pdm.backend.repositoriess;

import com.pdm.backend.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, String>, PagingAndSortingRepository<Person, String> {

    // Iterable<Person> FindAgeLess(int age);

    // @Query("SELECT p FROM person p WHERE p.age >= ?1")
    // Iterable<Person> FindTheOldestAge(int age);

}
