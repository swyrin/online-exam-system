package com.pdm.backend.services.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.pdm.backend.models.Exam;
import com.pdm.backend.models.Room;
import com.pdm.backend.repositoriess.ExamRepository;
import com.pdm.backend.repositoriess.RoomRepository;
import com.pdm.backend.services.RoomServices;

@Service
public class RoomServiceImple implements RoomServices {
      private RoomRepository roomRepository;
      private ExamRepository examRepository;
      
      public RoomServiceImple (RoomRepository roomRepository , ExamRepository examRepository){
         this.roomRepository = roomRepository;
         this.examRepository = examRepository;
      }

      @Override
      public Room saveRoom(String room_id , Room room){
         room.setRoomID(room_id);
         return roomRepository.save(room);
      }

      @Override
      public Optional<Room> findOne(String room_id){
        return roomRepository.findById(room_id);
      }

      @Override
      public List<Room> findAll(){
         return StreamSupport.stream(roomRepository.findAll().spliterator(), false).collect(Collectors.toList());
      }

      @Override
      public Room partialUpdated(String room_id , Room room){
        room.setRoomID(room_id);
        return roomRepository.findById(room_id).map(existingRoom ->{
            Optional.ofNullable(room.getHeadcount()).ifPresent(existingRoom::setHeadcount);
            Optional.ofNullable(room.getAttendees()).ifPresent(existingRoom::setAttendees);
            Optional.ofNullable(room.getAssignedExams()).ifPresent(existingRoom::setAssignedExams);
            Optional.ofNullable(room.getAssignedCourses()).ifPresent(existingRoom::setAssignedCourses);
            return roomRepository.save(existingRoom);
        }).orElseThrow(() -> new RuntimeException("No room found"));
      }

      @Override
      public boolean isExist(String room_id){
         return roomRepository.existsById(room_id);
      }

      @Override
      public void delete(String room_id){
         roomRepository.deleteById(room_id);
      }

      @Override
      public Room assignExamToRoom(String room_id , long exam_id){
         List<Exam> exams = null;
         Room roomEntity = roomRepository.findById(room_id).get();
         Exam examEntity = examRepository.findById(exam_id).get();
         exams = roomEntity.getAssignedExams();
         exams.add(examEntity);
         roomEntity.setAssignedExams(exams);
         return roomRepository.save(roomEntity);
      }

}
