package com.pdm.backend;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.pdm.backend.models.Course;
import com.pdm.backend.models.Person;
import com.pdm.backend.models.dto.coursesDto;
import com.pdm.backend.models.dto.personDto;

public final class TestDataUtil {
    private TestDataUtil(){
    }

    public static Course createTestCourseA(){
        return Course.builder()
                .CourseID(8904598L)
                .Name("Principle Of Database Management")
                .Abbreviation("PDM")
                // .Attendees(Attendees)
                .build();
    }

    public static coursesDto createTestCourseDtoA() {
        return coursesDto.builder()
                .CourseID(2316829L)
                .Name("Linear Algebra")
                .Abbreviation("LA")
                .build();
    }

    public static Course createTestCourseB() {
        return Course.builder()
                .CourseID(457833L)
                .Name("Technology Architecture")
                .Abbreviation("TA")
                .Attendees(Attendees)
                .build();
    }

    public static Course createTestCourseC() {
        return Course.builder()
                .CourseID(3442812L)
                .Name("Critical Thinking")
                .Abbreviation("CT")
                .Attendees(Attendees)
                .build();
    }

    public static Person createTestPersonA(){
        return Person.builder()
                .PersonID("ITITIU21085")
                .BirthDate(Date.valueOf("2003-06-12"))
                .Age(21)
                .FirstName("Fin")
                .MiddleName("Antony")
                .LastName("Jack")
                .Email("jack97@.com.email")
                .JoinDate(Date.valueOf("2022-08-19"))
                .Phone("094378902489")
                


                .build();
    }

    public static personDto createTestPersonDtoA(final personDto persondto){
        return personDto.builder()
                .PersonID("ITCSIU23067")
                .BirthDate(Date.valueOf("2003-09-12"))
                .Age(19)
                .FirstName("Sam")
                .MiddleName("Nicky")
                .LastName("Jane")
                .Email("KICM97@gmail.com")
                .JoinDate(Date.valueOf("2022-08-23"))
                .Phone("0948378232")
                .build();
    }

    public static Person createTestPersonB(){
        return Person.builder()
                .PersonID("ITDSIU22301")
                .BirthDate(Date.valueOf("2003-09-02"))
                .Age(20)
                .FirstName("Ferb")
                .MiddleName("Richter")
                .LastName("John")
                .Email("johnwich3@.com.email")
                .JoinDate(Date.valueOf("2022-08-23"))
                .Phone("094562375871")
                


                .build();
    }

    public static Person createTestPersonC(){
        return Person.builder()
                .PersonID("ITCSIU23001")
                .BirthDate(Date.valueOf("2003-12-11"))
                .Age(19)
                .FirstName("Karl")
                .MiddleName("Nick")
                .LastName("Mike")
                .Email("Mikelodic@.com.email")
                .JoinDate(Date.valueOf("2023-08-19"))
                .Phone("03785632198")
                


                .build();
    }
}