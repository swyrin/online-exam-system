package com.pdm.backend.services;

import java.util.List;
import java.util.Optional;




import com.pdm.backend.models.Person;

public interface PersonServices {

    

    
    Person createPersons(String person_id , Person person);
    List<Person> findAll();
    Optional<Person> findOne(String person_id);
    boolean isExist(String person_id);
    Person partialUpdatedPerson(String person_id , Person persons);
    void delete (String person_id);

}
