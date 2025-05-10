package com.pdm.backend.repositoriess;

import com.pdm.backend.models.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomRepository extends CrudRepository<Room, String>, PagingAndSortingRepository<Room, String> {


}
