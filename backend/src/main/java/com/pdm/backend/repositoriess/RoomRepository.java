package com.pdm.backend.repositoriess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pdm.backend.models.Room;

public interface RoomRepository extends CrudRepository<Room , String > , PagingAndSortingRepository<Room , String>
 {

    


}
