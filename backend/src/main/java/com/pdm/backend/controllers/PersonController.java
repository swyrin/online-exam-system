package com.pdm.backend.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pdm.backend.models.Person;
import com.pdm.backend.models.dto.personDto;
import com.pdm.backend.services.PersonServices;

import com.pdm.backend.mappers.Mapper;

@RestController
public class PersonController {

    private PersonServices personServices ;
    private Mapper<Person , personDto> personMapper;

    public PersonController(PersonServices personServices , Mapper<Person , personDto> personMapper){
        this.personServices = personServices;
        this.personMapper = personMapper;
    }


    @PutMapping("/person/{person_id}")
    public ResponseEntity<personDto> createPersons(@PathVariable("person_id") String person_id , @RequestBody personDto person )
    {
        Person personEntity = personMapper.mapfrom(person);
        boolean personExisted = personServices.isExist(person_id); 
        Person createdPerson= personServices.createPersons(person_id, personEntity);
        personDto persondto = personMapper.mapto(createdPerson);

        if(personExisted){
            return new ResponseEntity<>(persondto, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(persondto , HttpStatus.CREATED);
        }

        
    }

    @GetMapping(path = "/persons")
    public List<personDto> PersonList(){
        List<Person> persons = personServices.findAll();
        return persons.stream().map(personMapper::mapto).collect(Collectors.toList());
    }

    @GetMapping(path= "/persons/{person_id}")
    public ResponseEntity<personDto> getPersons(@PathVariable("person_id") String person_id , @RequestBody personDto persondto){

        
        Optional<Person> personEntity = personServices.findOne(person_id);
        return personEntity.map(person ->{
            personDto savedPersonDto = personMapper.mapto(person);
            return new ResponseEntity<>(savedPersonDto , HttpStatus.OK);
        }).orElse(
             new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
        
    }

    @PatchMapping(path = "/persons/{person_id}")
    public ResponseEntity<personDto> partialUpdate(@PathVariable("person_id") String person_id , @RequestBody personDto persodto){

        Optional<Person> personEntity = personServices.findOne(person_id);
        if(personEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        persodto.setPersonID(person_id);
        Person person = personMapper.mapfrom(persodto);
        Person updatedPerson = personServices.partialUpdatedPerson(person_id, person);
        return new ResponseEntity<>(personMapper.mapto(updatedPerson) , HttpStatus.OK);
    }

    @DeleteMapping(path= "/persons/{person_id}")
    public ResponseEntity<personDto> delete(@PathVariable("person_id") String person_id )
    {
        personServices.delete(person_id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;

    }

}
