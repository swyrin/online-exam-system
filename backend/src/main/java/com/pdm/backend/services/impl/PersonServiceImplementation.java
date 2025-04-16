package com.pdm.backend.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;
import org.springframework.stereotype.Service;

import com.pdm.backend.models.Person;
import com.pdm.backend.repositoriess.PersonRepository;
import com.pdm.backend.services.PersonServices;

@Service
public class PersonServiceImplementation implements PersonServices{


    private PersonRepository personRepository ;

    public PersonServiceImplementation(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public Person createPersons(String person_id , Person person){
        person.setPersonID(person_id);
        return personRepository.save(person);
    }

    @Override 
    public List<Person> findAll(){
          return StreamSupport.stream(personRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override 
    public Optional<Person> findOne(String person_id){
        return personRepository.findById(person_id);
    }

    @Override
    public boolean isExist(String person_id){
        return personRepository.existsById(person_id);
    }

    @Override
    public Person partialUpdatedPerson(String person_id , Person persons){
            persons.setPersonID(person_id);

           return  personRepository.findById(person_id).map(existingPerson ->{
                Optional.ofNullable(persons.getFirstName()).ifPresent(existingPerson::setFirstName);
                Optional.ofNullable(persons.getLastName()).ifPresent(existingPerson::setLastName);
                Optional.ofNullable(persons.getPhone()).ifPresent(existingPerson::setPhone);
                Optional.ofNullable(persons.getBirthDate()).ifPresent(existingPerson::setBirthDate);
                return personRepository.save(existingPerson);
            }).orElseThrow(() -> new RuntimeException("Person does not exist"));

            
    }

    @Override
    public void delete(String person_id){
        personRepository.deleteById(person_id);
    }
}
    

