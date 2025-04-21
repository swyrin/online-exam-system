package com.pdm.backend.controllers;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pdm.backend.services.CourseServices;
import com.pdm.backend.services.ExamServices;
import com.pdm.backend.services.PersonServices;
import com.pdm.backend.services.RoomServices;
import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Room;
import com.pdm.backend.models.dto.RoomDto;

@RestController
public class RoomController {
     
    private RoomServices roomServices;
    private Mapper<Room , RoomDto> roomMapper;
    private CourseServices courseServices;
    private PersonServices personServices;
    private ExamServices examServices;
    
   
    public RoomController(RoomServices roomServices , Mapper<Room , RoomDto> roomMapper , CourseServices courseServices , PersonServices personServices , ExamServices examServices
    ){
        this.roomServices = roomServices;
        this.roomMapper = roomMapper;
        this.courseServices = courseServices;
        this.personServices = personServices;
        this.examServices = examServices;
    }

    @PutMapping(path = "/rooms/{room_id}")
    public ResponseEntity<RoomDto> create(@PathVariable("room_id") String room_id , RoomDto roomDto){
        Room roomEntity = roomMapper.mapfrom(roomDto);
        Room savedRoomEntity = roomServices.saveRoom(room_id, roomEntity);
        return new ResponseEntity<>(roomMapper.mapto(savedRoomEntity) , HttpStatus.CREATED);
    }

    @GetMapping(path = "/rooms")
    public List<RoomDto> get(){
        List<Room> result = roomServices.findAll();
        return result.stream().map(roomMapper::mapto).collect(Collectors.toList());
    }

    @GetMapping(path = "/rooms/{room_id}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable("room_id") String room_id , @RequestBody RoomDto roomDto){
        
        Optional<Room> found = roomServices.findOne(room_id);
        if(!found.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            Room room = roomMapper.mapfrom(roomDto);
            Room savedRoom = roomServices.saveRoom(room_id, room);

            return new ResponseEntity<>(roomMapper.mapto(savedRoom),HttpStatus.FOUND);
        }
    }

    // @PutMapping (path = "/rooms/{room_id}")
    // public ResponseEntity<RoomDto> fullUpdated(@PathVariable("room_id") String room_id , RoomDto roomDto){
             
    //       boolean isExistRoom = roomServices.isExist(room_id);

    //       if(!isExistRoom)
    //       {
    //          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //       }

    //       roomDto.setRoomID(room_id);
    //       Room roomEntity = roomMapper.mapfrom(roomDto);
    //       Room savedRoomEntity = roomServices.saveRoom(room_id, roomEntity);
    //      return new ResponseEntity<>(roomMapper.mapto(savedRoomEntity) , HttpStatus.OK);
    // }

    @PatchMapping(path = "/rooms/{room_id}")
    public ResponseEntity<RoomDto> partialUpdatedRoom(@PathVariable("room_id") String room_id , @RequestBody RoomDto roomDto){

        boolean isExistRoom = roomServices.isExist(room_id);

          if(!isExistRoom)
          {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }

          roomDto.setRoomID(room_id);
          Room roomEntity = roomMapper.mapfrom(roomDto);
          Room partialUpdatedRoom = roomServices.partialUpdated(room_id, roomEntity);
          return new ResponseEntity<>(roomMapper.mapto(partialUpdatedRoom) , HttpStatus.OK);
    }

    @DeleteMapping(path ="/rooms/{room_id}")
    public ResponseEntity delete(@PathVariable("room_id") String room_id){
        roomServices.delete(room_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping (path = "/rooms/{room_id}/exams/{exam_id}")
    public ResponseEntity<RoomDto> assignExamsToRoom(
        @PathVariable("room_id") String room_id ,
        @PathVariable("exam_id") long exam_id,
        @RequestBody RoomDto roomDto
    ){
        boolean foundRoomID = roomServices.isExist(room_id);
        boolean foundExamID = examServices.isExist(exam_id);
        if(foundExamID && foundRoomID){
            Room room = roomServices.assignExamToRoom(room_id, exam_id);
            return new ResponseEntity<>(roomMapper.mapto(room) , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
