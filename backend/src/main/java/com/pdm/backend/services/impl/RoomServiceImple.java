package com.pdm.backend.services.impl;


import com.pdm.backend.models.Course;
import com.pdm.backend.models.Exam;
import com.pdm.backend.models.Person;
import com.pdm.backend.models.Room;
import com.pdm.backend.repositoriess.CourseRepository;
import com.pdm.backend.repositoriess.ExamRepository;
import com.pdm.backend.repositoriess.PersonRepository;
import com.pdm.backend.repositoriess.RoomRepository;
import com.pdm.backend.services.RoomServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RoomServiceImple implements RoomServices {
    private final RoomRepository roomRepository;
    private final ExamRepository examRepository;
    private final PersonRepository personRepository;
    private final CourseRepository courseRepository;

    public RoomServiceImple(RoomRepository roomRepository, ExamRepository examRepository, PersonRepository personRepository, CourseRepository courseRepository) {
        this.roomRepository = roomRepository;
        this.examRepository = examRepository;
        this.personRepository = personRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Room saveRoom(String room_id, Room room) {
        room.setRoomID(room_id);
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> findOne(String room_id) {
        return roomRepository.findById(room_id);
    }

    @Override
    public Page<Room> findAll(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    @Override
    public Room partialUpdated(String room_id, Room room) {
        room.setRoomID(room_id);
        return roomRepository.findById(room_id).map(existingRoom -> {
            Optional.ofNullable(room.getHeadcount()).ifPresent(existingRoom::setHeadcount);
            Optional.ofNullable(room.getAttendees()).ifPresent(existingRoom::setAttendees);
            Optional.ofNullable(room.getAssignedExams()).ifPresent(existingRoom::setAssignedExams);
            Optional.ofNullable(room.getAssignedCourses()).ifPresent(existingRoom::setAssignedCourses);
            return roomRepository.save(existingRoom);
        }).orElseThrow(() -> new RuntimeException("No room found"));
    }

    @Override
    public boolean isExist(String room_id) {
        return roomRepository.existsById(room_id);
    }

    @Override
    public void delete(String room_id) {
        roomRepository.deleteById(room_id);
    }

    @Override
    public Room assignExamToRoom(String room_id, String exam_id) {
        Set<Exam> exams = null;
        Room roomEntity = roomRepository.findById(room_id).get();
        Exam examEntity = examRepository.findById(exam_id).get();
        exams = roomEntity.getAssignedExams();
        exams.add(examEntity);
        roomEntity.setAssignedExams(exams);
        return roomRepository.save(roomEntity);
    }

    @Override
    public Room assignPersonToRoom(String room_id, String person_id, Room room) {
        Set<Person> persons = null;
        room.setRoomID(room_id);
        room = roomRepository.findById(room_id).get();
        Person personEntity = personRepository.findById(person_id).get();
        persons = room.getAttendees();
        persons.add(personEntity);
        room.setAttendees(persons);
        return roomRepository.save(room);


    }

    @Override
    public Room assignCourseToRoom(String room_id, String course_id, Room room) {
        Set<Course> courses = null;
        room.setRoomID(room_id);
        room = roomRepository.findById(room_id).get();
        Course courseEntity = courseRepository.findById(course_id).get();
        courses = room.getAssignedCourses();
        courses.add(courseEntity);
        room.setAssignedCourses(courses);
        return roomRepository.save(room);
    }

}
