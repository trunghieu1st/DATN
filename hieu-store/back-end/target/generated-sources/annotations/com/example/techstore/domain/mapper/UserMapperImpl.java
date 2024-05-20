package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.UserCreateDto;
import com.example.techstore.domain.dto.request.UserUpdateDto;
import com.example.techstore.domain.dto.response.UserDto;
import com.example.techstore.domain.entity.Role;
import com.example.techstore.domain.entity.User;
import com.example.techstore.domain.entity.User.UserBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapUserCreateDtoToUser(UserCreateDto userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.username( userCreateDTO.getUsername() );
        user.password( userCreateDTO.getPassword() );
        user.phone( userCreateDTO.getPhone() );
        user.email( userCreateDTO.getEmail() );

        return user.build();
    }

    @Override
    public UserDto mapUserToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setRoleName( userRoleName( user ) );
        userDto.setCreatedDate( user.getCreatedDate() );
        userDto.setLastModifiedDate( user.getLastModifiedDate() );
        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setFullName( user.getFullName() );
        if ( user.getGender() != null ) {
            userDto.setGender( String.valueOf( user.getGender() ) );
        }
        userDto.setBirthday( user.getBirthday() );
        userDto.setPhone( user.getPhone() );
        userDto.setEmail( user.getEmail() );
        userDto.setAvatar( user.getAvatar() );

        return userDto;
    }

    @Override
    public List<UserDto> mapUsersToUserDtos(List<User> user) {
        if ( user == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( user.size() );
        for ( User user1 : user ) {
            list.add( mapUserToUserDto( user1 ) );
        }

        return list;
    }

    @Override
    public void updateUser(User user, UserUpdateDto userUpdateDto) {
        if ( userUpdateDto == null ) {
            return;
        }

        if ( userUpdateDto.getBirthday() != null ) {
            user.setBirthday( LocalDate.parse( userUpdateDto.getBirthday(), DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) ) );
        }
        if ( userUpdateDto.getUsername() != null ) {
            user.setUsername( userUpdateDto.getUsername() );
        }
        if ( userUpdateDto.getFullName() != null ) {
            user.setFullName( userUpdateDto.getFullName() );
        }
        if ( userUpdateDto.getGender() != null ) {
            user.setGender( userUpdateDto.getGender() );
        }
        if ( userUpdateDto.getPhone() != null ) {
            user.setPhone( userUpdateDto.getPhone() );
        }
        if ( userUpdateDto.getEmail() != null ) {
            user.setEmail( userUpdateDto.getEmail() );
        }
    }

    private String userRoleName(User user) {
        if ( user == null ) {
            return null;
        }
        Role role = user.getRole();
        if ( role == null ) {
            return null;
        }
        String name = role.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
