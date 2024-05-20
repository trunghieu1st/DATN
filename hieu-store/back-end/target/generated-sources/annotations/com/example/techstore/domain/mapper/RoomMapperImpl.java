package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.response.RoomDto;
import com.example.techstore.domain.entity.Room;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T01:31:31+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomDto mapRoomToRoomDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDto roomDto = new RoomDto();

        roomDto.setCreatedDate( room.getCreatedDate() );
        roomDto.setLastModifiedDate( room.getLastModifiedDate() );
        roomDto.setId( room.getId() );

        return roomDto;
    }

    @Override
    public List<RoomDto> mapRoomToRoomDto(List<Room> rooms) {
        if ( rooms == null ) {
            return null;
        }

        List<RoomDto> list = new ArrayList<RoomDto>( rooms.size() );
        for ( Room room : rooms ) {
            list.add( mapRoomToRoomDto( room ) );
        }

        return list;
    }
}
