package com.pdm.backend.services;

import com.pdm.backend.models.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoomServices {


    Room saveRoom(String room_id, Room room);

    Optional<Room> findOne(String room_id);

    Page<Room> findAll(Pageable pageable);

    Room partialUpdated(String room_id, Room room);

    boolean isExist(String room_id);

    void delete(String room_id);

    Room assignExamToRoom(String room_id, String exam_id);

    Room assignPersonToRoom(String room_id, String person_id, Room room);

    Room assignCourseToRoom(String room_id, String course_id, Room room);

}
