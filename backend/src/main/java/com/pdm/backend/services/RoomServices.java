package com.pdm.backend.services;

import java.util.List;
import java.util.Optional;

import com.pdm.backend.models.Room;

public interface RoomServices {

    

       Room saveRoom(String room_id , Room room);
       Optional<Room> findOne(String room_id);
       List<Room> findAll();
       Room partialUpdated(String room_id , Room room);
       boolean isExist(String room_id);
       void delete(String room_id);
       Room assignExamToRoom(String room_id , long exam_id);

}
