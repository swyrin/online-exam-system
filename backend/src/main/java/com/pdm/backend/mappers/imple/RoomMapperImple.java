package com.pdm.backend.mappers.imple;

import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Room;
import com.pdm.backend.models.dto.RoomDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoomMapperImple implements Mapper<Room, RoomDto> {
    private ModelMapper modelmapper;

    @Override
    public Room mapfrom(RoomDto roomDto) {
        return modelmapper.map(roomDto, Room.class);
    }

    @Override
    public RoomDto mapto(Room room) {
        return modelmapper.map(room, RoomDto.class);
    }

}
