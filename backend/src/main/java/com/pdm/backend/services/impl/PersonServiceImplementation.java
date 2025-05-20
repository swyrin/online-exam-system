package com.pdm.backend.services.impl;

import com.pdm.backend.models.Course;
import com.pdm.backend.models.Exam;
import com.pdm.backend.models.Person;
import com.pdm.backend.repositoriess.CourseRepository;
import com.pdm.backend.repositoriess.ExamRepository;
import com.pdm.backend.repositoriess.PersonRepository;
import com.pdm.backend.services.PersonServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PersonServiceImplementation implements PersonServices {


    private final PersonRepository personRepository;
    private final CourseRepository courseRepository;
    private final ExamRepository examRepository;

    public PersonServiceImplementation(PersonRepository personRepository, CourseRepository courseRepository, ExamRepository examRepository) {
        this.personRepository = personRepository;
        this.courseRepository = courseRepository;
        this.examRepository = examRepository;
    }

    @Override
    public Person createPersons(String person_id, Person person) {
        person.setPersonID(person_id);
        return personRepository.save(person);
    }

    @Override
    public Page<Person> findAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Override
    public Optional<Person> findOne(String person_id) {
        return personRepository.findById(person_id);
    }

    @Override
    public boolean isExist(String person_id) {
        return personRepository.existsById(person_id);
    }

    @Override
    public Person partialUpdatedPerson(String person_id, Person persons) {
        persons.setPersonID(person_id);

        return personRepository.findById(person_id).map(existingPerson -> {
            Optional.ofNullable(persons.getFirstName()).ifPresent(existingPerson::setFirstName);
            Optional.ofNullable(persons.getLastName()).ifPresent(existingPerson::setLastName);
            Optional.ofNullable(persons.getPhone()).ifPresent(existingPerson::setPhone);
            Optional.ofNullable(persons.getBirthDate()).ifPresent(existingPerson::setBirthDate);
            return personRepository.save(existingPerson);
        }).orElseThrow(() -> new RuntimeException("Person does not exist"));


    }

    @Override
    public void delete(String person_id) {
        personRepository.deleteById(person_id);
    }

    @Override
    public Person assignCourseToPerson(String person_id, String course_id) {
        Set<Course> courseEnrolled = null;
        Person personEntity = personRepository.findById(person_id).get();
        Course courseEntity = courseRepository.findById(course_id).get();
        courseEnrolled = personEntity.getEnrolledCourses();
        courseEnrolled.add(courseEntity);
        personEntity.setEnrolledCourses(courseEnrolled);
        return personRepository.save(personEntity);

    }

    @Override
    public Person assignExamToPerson(String person_id, String exam_id) {
        Set<Exam> examAssigned = null;
        Person personEntity = personRepository.findById(person_id).get();
        Exam examEntity = examRepository.findById(exam_id).get();
        examAssigned = personEntity.getAssignedExams();
        examAssigned.add(examEntity);
        personEntity.setAssignedExams(examAssigned);
        return personRepository.save(personEntity);
    }

    @Override
    public Person setPersonReports(String person_id, String course_id) {
        return null;
    }
}
    

