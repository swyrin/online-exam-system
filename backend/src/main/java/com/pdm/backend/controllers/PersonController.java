package com.pdm.backend.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.pdm.backend.models.Exam;
import com.pdm.backend.models.Person;
import com.pdm.backend.models.dto.personDto;
import com.pdm.backend.services.CourseServices;
import com.pdm.backend.services.ExamServices;
import com.pdm.backend.services.PersonServices;

import com.pdm.backend.mappers.Mapper;

@RestController
public class PersonController {

    private PersonServices personServices ;
    private Mapper<Person , personDto> personMapper;
    private CourseServices courseServices;
    private ExamServices examServices;

    public PersonController(PersonServices personServices , Mapper<Person , personDto> personMapper ,CourseServices courseServices , ExamServices examServices
    ){
        this.personServices = personServices;
        this.personMapper = personMapper;
        this.courseServices = courseServices;
        this.examServices = examServices;
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
    public Page<personDto> PersonList(Pageable pageable){
        Page<Person> person = personServices.findAll(pageable);
        return person.map(personMapper::mapto);
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


    //for assigned course to person 
    @PutMapping(path = "/persons/{person_id}/courses/{course_id}")
    public ResponseEntity<personDto> assignCoursesToPerson(
         @PathVariable("person_id") String person_id,
         @PathVariable("course_id") String course_id,
         @RequestBody personDto personDto ) {
            
            Boolean foundCourseID = courseServices.isExist(course_id);
            Boolean foundPersonID = personServices.isExist(person_id);
            if((foundCourseID && foundPersonID)){
                Person person =personServices.assignCourseToPerson(person_id , course_id);
                return new ResponseEntity<>(personMapper.mapto(person) , HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
         }

         //for assign Exam to Person 
    @PutMapping(path ="/persons/{person_id}/exams/{exam_id}")
    public ResponseEntity<personDto> assignExamsToPerson(
        @PathVariable("person_id") String person_id,
        @PathVariable("exam_id") long exam_id,
        @RequestBody personDto personDto){
              boolean foundPersonID = personServices.isExist(person_id);
              boolean foundExamID = examServices.isExist(exam_id);
              if(foundExamID && foundPersonID){
                 Person person = personServices.assignExamToPerson(person_id, exam_id);
                 return new ResponseEntity<>(personMapper.mapto(person) , HttpStatus.OK);
              }
              else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
              }

        }
    
        


}
