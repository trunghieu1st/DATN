package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.response.RoomDto;
import com.example.techstore.domain.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomMapper {
    RoomDto mapRoomToRoomDto(Room room);

    List<RoomDto> mapRoomToRoomDto(List<Room> rooms);
}