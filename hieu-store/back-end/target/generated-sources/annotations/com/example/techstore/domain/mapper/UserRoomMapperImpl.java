package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.response.UserRoomDto;
import com.example.techstore.domain.entity.Room;
import com.example.techstore.domain.entity.User;
import com.example.techstore.domain.entity.UserRoom;
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
public class UserRoomMapperImpl implements UserRoomMapper {

    @Override
    public UserRoomDto mapUserRoomToUserRoomDto(UserRoom userRoom) {
        if ( userRoom == null ) {
            return null;
        }

        UserRoomDto userRoomDto = new UserRoomDto();

        userRoomDto.setUserId( userRoomUserId( userRoom ) );
        userRoomDto.setRoomId( userRoomRoomId( userRoom ) );
        userRoomDto.setCreatedDate( userRoom.getCreatedDate() );
        userRoomDto.setLastModifiedDate( userRoom.getLastModifiedDate() );

        return userRoomDto;
    }

    @Override
    public List<UserRoomDto> mapUserRoomToUserRoomDto(List<UserRoom> userRooms) {
        if ( userRooms == null ) {
            return null;
        }

        List<UserRoomDto> list = new ArrayList<UserRoomDto>( userRooms.size() );
        for ( UserRoom userRoom : userRooms ) {
            list.add( mapUserRoomToUserRoomDto( userRoom ) );
        }

        return list;
    }

    private String userRoomUserId(UserRoom userRoom) {
        if ( userRoom == null ) {
            return null;
        }
        User user = userRoom.getUser();
        if ( user == null ) {
            return null;
        }
        String id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String userRoomRoomId(UserRoom userRoom) {
        if ( userRoom == null ) {
            return null;
        }
        Room room = userRoom.getRoom();
        if ( room == null ) {
            return null;
        }
        String id = room.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
