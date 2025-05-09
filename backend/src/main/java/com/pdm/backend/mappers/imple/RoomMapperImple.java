package com.pdm.backend.mappers.imple;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Room;
import com.pdm.backend.models.dto.RoomDto;

@Component
public class RoomMapperImple implements Mapper<Room, RoomDto> {
     private ModelMapper modelmapper;
     public RoomMapperImple(ModelMapper modelMapper){
          this.modelmapper = modelMapper;
     }

     @Override
     public Room mapfrom(RoomDto roomDto){
        return modelmapper.map(roomDto, Room.class);
     }

     @Override
     public RoomDto mapto(Room room){
        return modelmapper.map(room, RoomDto.class);
     }

}
